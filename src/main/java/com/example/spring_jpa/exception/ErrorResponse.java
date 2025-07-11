package com.example.spring_jpa.exception;

import lombok.Builder;

import java.time.LocalDate;


@Builder
public record ErrorResponse<T>(
        String message,
        Integer code,
        LocalDate timestamp,
        T details
) {
}
