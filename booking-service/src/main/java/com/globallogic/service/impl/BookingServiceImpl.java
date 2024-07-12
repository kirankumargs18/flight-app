package com.globallogic.service.impl;

import com.globallogic.client.FlightServiceClient;
import com.globallogic.client.PassengerServiceClient;
import com.globallogic.entity.Booking;
import com.globallogic.exception.BookingNotFoundException;
import com.globallogic.exception.FlightNotFoundException;
import com.globallogic.exception.PassengerNotFoundException;
import com.globallogic.kafka.BookingProducer;
import com.globallogic.payload.BookingDTO;
import com.globallogic.payload.BookingEvent;
import com.globallogic.payload.Flight;
import com.globallogic.payload.Passenger;
import com.globallogic.repository.BookingRepository;
import com.globallogic.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerServiceClient passengerServiceClient;

    @Autowired
    private FlightServiceClient flightServiceClient;

    @Autowired
    private BookingProducer bookingProducer;

    @Override
    public Booking addBooking(Booking booking) {
        String passengerEmail = validatePassenger(booking.getPassengerId());
        validateFlight(booking.getFlightId());
        Booking savedBooking = bookingRepository.save(booking);

        BookingEvent bookingEvent = convertBookingToBookingEvent(booking, passengerEmail);
        bookingProducer.sendMessage(bookingEvent);

        return savedBooking;
    }

    @Override
    public Booking getBookingByBookingId(long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID : " + id + " not found"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBookingByBookId(long id, Booking booking) {
        validatePassenger(booking.getPassengerId());
        validateFlight(booking.getFlightId());
        Booking booking1 = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID : " + id + " not found"));
        booking1.setFlightId(booking.getFlightId());
        booking1.setBookingDate(booking.getBookingDate());
        booking1.setTotalPrice(booking.getTotalPrice());
        return bookingRepository.save(booking1);
    }

    @Override
    public void deleteBooking(long id) {
        bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found with ID : " + id + " not found"));
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getAllBookingsByPassengerId(long passengerId) {
        validatePassenger(passengerId);
        return bookingRepository.findByPassengerId(passengerId);
    }

    private String validatePassenger(long id) {
        ResponseEntity<Passenger> passengerResponseEntity = passengerServiceClient.getPassengerById(id);
        if (!passengerResponseEntity.getStatusCode().is2xxSuccessful() || passengerResponseEntity.getBody() == null) {
            throw new PassengerNotFoundException("Passenger with ID : " + id + " not found");
        }
        return passengerResponseEntity.getBody().getEmail();
    }

    private void validateFlight(long id) {
        ResponseEntity<Flight> flightResponseEntity = flightServiceClient.getFlightById(id);
        if (!flightResponseEntity.getStatusCode().is2xxSuccessful() || flightResponseEntity.getBody() == null) {
            throw new FlightNotFoundException("Flight with ID : " + id + " not found");
        }
    }

    private BookingEvent convertBookingToBookingEvent(Booking booking, String email) {
        BookingEvent bookingEvent = new BookingEvent();
        bookingEvent.setEmail(email);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setPassengerId(booking.getPassengerId());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingEvent.setBookingDTO(bookingDTO);
        return bookingEvent;
    }

}
