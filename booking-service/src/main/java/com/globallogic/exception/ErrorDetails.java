package com.globallogic.exception;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDetails {

    private LocalDate timestamp;

    private String message;

    private String details;
}
