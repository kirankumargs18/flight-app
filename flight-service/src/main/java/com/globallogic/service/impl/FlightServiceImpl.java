package com.globallogic.service.impl;

import com.globallogic.entity.Flight;
import com.globallogic.exception.FlightNotFoundException;
import com.globallogic.repository.FlightRepository;
import com.globallogic.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(long flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight with ID : " + flightId + " not found"));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight updateFlight(long flightId, Flight flight) {
        Flight flight1 = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight with ID : " + flightId + " not found"));
        flight1.setArrivalDate(flight.getArrivalDate());
        flight1.setArrivalTime(flight.getArrivalTime());
        flight1.setDepartureDate(flight.getDepartureDate());
        flight1.setDepartureTime(flight.getDepartureTime());
        return flightRepository.save(flight1);
    }

    @Override
    public void removeFlight(long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight with ID : " + flightId + " not found"));
        flightRepository.deleteById(flightId);
    }
}
