package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        boolean existsByEmail(String email);
        boolean existsByPhoneNumber(String phoneNumber);
        Optional<Customer> findByPhoneNumber(String phoneNumber);
}
