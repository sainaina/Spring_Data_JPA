package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.Customer;
import com.example.spring_jpa.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KYCRepository
        extends JpaRepository<KYC,String> {
    boolean existsByNationalCardId(String nationalCardId);
    Optional<KYC> findByCustomerId(Integer customerId);
}
