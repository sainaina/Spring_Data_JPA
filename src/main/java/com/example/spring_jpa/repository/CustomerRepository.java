package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

        List<Customer> findAllByIsDeletedFalse();

        boolean existsByEmail(String email);
        boolean existsByPhoneNumber(String phoneNumber);
        Optional<Customer> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);
        Optional<Customer> findByPhoneNumber(String phoneNumber);
        @Modifying
        @Query( value = """
            UPDATE Customer c
            SET c.isDeleted =TRUE
            WHERE c.phoneNumber = ?1
        """
        )
        void disableAccountByPhoneNumber(String phoneNumber);
}
