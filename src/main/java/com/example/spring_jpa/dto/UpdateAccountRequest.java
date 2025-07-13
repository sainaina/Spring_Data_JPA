package com.example.spring_jpa.dto;

import java.math.BigDecimal;

public record UpdateAccountRequest(
        BigDecimal balance,
        BigDecimal overLimit,
        Boolean isDeleted
) {
}
