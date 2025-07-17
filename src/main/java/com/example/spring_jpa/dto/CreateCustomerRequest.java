package com.example.spring_jpa.dto;

public record CreateCustomerRequest(
        String fullName,
        String gender,
        String dob,
        String email,
        String phoneNumber,
        String remark,
        String nationalCardId,
        String customerSegment
) {}
