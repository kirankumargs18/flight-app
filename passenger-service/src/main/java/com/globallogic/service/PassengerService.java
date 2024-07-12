package com.globallogic.service;

import com.globallogic.entity.Passenger;

import java.util.List;

public interface PassengerService {

    Passenger savePassenger(Passenger passenger);

    Passenger getPassengerById(long id);

    List<Passenger> getAllPassengers();

    Passenger updatePassengerById(long id, Passenger passenger);

    void deletePassengerById(long id);
}
