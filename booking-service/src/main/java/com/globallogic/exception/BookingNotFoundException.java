package com.globallogic.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BookingNotFoundException extends RuntimeException{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
