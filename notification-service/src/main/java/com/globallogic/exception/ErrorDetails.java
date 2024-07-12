package com.globallogic.exception;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private LocalDate timestamp;

    private String message;

    private String details;
}
