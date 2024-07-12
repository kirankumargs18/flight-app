package com.globallogic.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flight {

    private Long flightId;
    private String departureTime;
    private String arrivalTime;
    private String departureDate;
    private String arrivalDate;
}
