package com.example.spring_jpa.repository;

import com.example.spring_jpa.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer>{

}
