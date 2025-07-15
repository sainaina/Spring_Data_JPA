package com.example.spring_jpa.dto;


import lombok.Builder;

@Builder
public record CustomerResponse(
        String fullName,
        String gender,
        String email,
        String phoneNumber,
        String segment) {
}
