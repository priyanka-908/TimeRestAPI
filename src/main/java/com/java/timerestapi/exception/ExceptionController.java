package com.java.timerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity NotFoundTimeZoneException(HttpClientErrorException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getResponseBodyAsString());
    }

    @ExceptionHandler(value = NotUSTimeZoneException.class)
    public ResponseEntity<Object> NotUSTimeZoneException(NotUSTimeZoneException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
