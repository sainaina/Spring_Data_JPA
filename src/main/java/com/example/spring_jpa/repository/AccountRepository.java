package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByAccNo(String accNo);
    Optional<Account> findByAccNo(String accNo);
}
