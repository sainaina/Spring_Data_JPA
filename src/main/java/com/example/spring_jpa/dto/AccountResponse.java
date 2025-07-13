package com.example.spring_jpa.dto;

import com.example.spring_jpa.domain.AccountType;

import java.math.BigDecimal;

public record AccountResponse(
        String fullName,
        String phoneNumber,
        String accNo,
        BigDecimal balance,
        BigDecimal overLimit,
        Boolean isDeleted
) {}

