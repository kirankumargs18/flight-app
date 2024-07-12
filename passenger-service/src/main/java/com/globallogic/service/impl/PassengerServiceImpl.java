package com.globallogic.service.impl;

import com.globallogic.entity.Passenger;
import com.globallogic.exception.PassengerNotFoundException;
import com.globallogic.repository.PassengerRepository;
import com.globallogic.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger getPassengerById(long id) {
        return passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException("Passenger with id : " + id + " not found"));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger updatePassengerById(long id, Passenger passenger) {
        Passenger passenger1 = passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException("Passenger with id : " + id + " not found"));
        passenger1.setEmail(passenger.getEmail());
        passenger1.setLastName(passenger.getLastName());
        passenger1.setFirstName(passenger.getFirstName());
        return passengerRepository.save(passenger1);
    }

    @Override
    public void deletePassengerById(long id) {
        passengerRepository.findById(id).orElseThrow(() -> new PassengerNotFoundException("Passenger with id : " + id + " not found"));
        passengerRepository.deleteById(id);
    }
}
