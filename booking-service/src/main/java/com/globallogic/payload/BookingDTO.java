package com.globallogic.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDTO {

    private Long passengerId;

    private String bookingDate;

    private Double totalPrice;
}
