package com.example.spring_jpa.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;


@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceException(
            ResponseStatusException e
    ) {
        ErrorResponse<?> error = ErrorResponse.builder()
                .message("Service business logic error")
                .code(e.getStatusCode().value())
                .timestamp(LocalDate.now())
                .details(e.getReason())
                .build();


        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(error);
    }
}
