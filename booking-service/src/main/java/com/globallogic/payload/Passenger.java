package com.globallogic.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Passenger {

    private Long passengerId;
    private String firstName;
    private String lastName;
    private String email;
}
