package com.example.spring_jpa.service.impl;

import com.example.spring_jpa.domain.KYC;
import com.example.spring_jpa.repository.KYCRepository;
import com.example.spring_jpa.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {

    private final KYCRepository kycRepository;

    @Transactional
    @Override
    public void verifyKYCByCustomerId(Integer customerId) {
        KYC kyc = kycRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KYC not found for customer id " + customerId));
        kyc.setIsVerified(true);
        kycRepository.save(kyc);
    }
}
