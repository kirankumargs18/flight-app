package com.globallogic.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleFlightNotFoundException(PassengerNotFoundException exception, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {

            ex.getBindingResult().getAllErrors().forEach((error) -> {

                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                map.put(fieldName, message);
            });
        });
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
