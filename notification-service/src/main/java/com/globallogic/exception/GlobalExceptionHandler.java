package com.globallogic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ErrorDetails> handleMailException(MailException mailException, WebRequest webRequest) {

        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), mailException.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleMailException(Exception exception, WebRequest webRequest) {

        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
