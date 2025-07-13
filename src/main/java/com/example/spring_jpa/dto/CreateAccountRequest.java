package com.example.spring_jpa.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String fullName,
        String phoneNumber,
        String accNo,
        BigDecimal balance,
        BigDecimal overLimit,
        Boolean isDeleted
) {
}
