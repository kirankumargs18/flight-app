package com.globallogic.util;

import com.globallogic.entity.Flight;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

public class TimeValidatorImpl implements ConstraintValidator<TimeValidator, Flight> {
    @Override
    public boolean isValid(Flight flight, ConstraintValidatorContext context) {

        try {
            LocalTime departureTime = LocalTime.parse(flight.getDepartureTime());
            LocalTime arrivalTime = LocalTime.parse(flight.getArrivalTime());

            if (!arrivalTime.isBefore(departureTime)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Arrival time should be less than departure time")
                        .addPropertyNode("arrivalTime")
                        .addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
