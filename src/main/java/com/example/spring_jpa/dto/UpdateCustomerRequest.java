package com.example.spring_jpa.dto;

public record UpdateCustomerRequest(
        String fullName,
        String phoneNumber,
        String remark
) {
}
