package com.globallogic.service;

import com.globallogic.entity.Flight;

import java.util.List;

public interface FlightService {

    Flight addFlight(Flight flight);

    Flight getFlightById(long flightId);

    List<Flight> getAllFlights();

    Flight updateFlight(long flightId, Flight flight);

    void removeFlight(long flightId);
}
