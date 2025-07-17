package com.example.spring_jpa.dto;

import com.example.spring_jpa.domain.AccountType;

import java.math.BigDecimal;

public record AccountResponse(
        String actNo,
        String actName,
        String actCurrency,
        BigDecimal balance,
        Boolean isHide,
        String accountType
) {
}