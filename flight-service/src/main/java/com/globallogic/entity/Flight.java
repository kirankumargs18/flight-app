package com.globallogic.entity;

import com.globallogic.util.TimeValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@TimeValidator(message = "Departure time should be greaterthan arrival time")
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotBlank(message = "departureTime cannot be null or empty string")
    private String departureTime;

    @NotBlank(message = "arrivalTime cannot be null or empty string")
    private String arrivalTime;

    @NotBlank(message = "departureDate cannot be null or empty string")
    private String departureDate;

    @NotBlank(message = "arrivalDate cannot be null or empty string")
    private String arrivalDate;
}
