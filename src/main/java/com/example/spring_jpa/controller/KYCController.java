package com.example.spring_jpa.controller;

import com.example.spring_jpa.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kyc")
@RequiredArgsConstructor
public class KYCController {

    private final KYCService kycService;

    @PutMapping("/verify/{customerId}")
    public ResponseEntity<String> verifyKYC(@PathVariable Integer customerId) {
        kycService.verifyKYCByCustomerId(customerId);
        return ResponseEntity.ok("KYC has been verified successfully");
    }
}
