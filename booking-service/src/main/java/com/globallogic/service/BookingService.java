package com.globallogic.service;

import com.globallogic.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking addBooking(Booking booking);

    Booking getBookingByBookingId(long id);

    List<Booking> getAllBookings();

    Booking updateBookingByBookId(long id, Booking booking);

    void deleteBooking(long id);

    List<Booking> getAllBookingsByPassengerId(long passengerId);
}
