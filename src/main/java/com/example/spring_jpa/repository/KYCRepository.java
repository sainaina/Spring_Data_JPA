package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCRepository
        extends JpaRepository<KYC,Integer> {

}
