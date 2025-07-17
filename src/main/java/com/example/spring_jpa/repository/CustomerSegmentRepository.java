package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {
    Optional<CustomerSegment> findBySegment(String s);
}
