package com.example.spring_jpa.dto;

public record CreateCustomerRequest(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String remark,
        String nationalCardId,
        String segment
) {}
