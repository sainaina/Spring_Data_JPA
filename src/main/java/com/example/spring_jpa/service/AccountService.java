package com.example.spring_jpa.service;

import com.example.spring_jpa.dto.AccountResponse;
import com.example.spring_jpa.dto.CreateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAll();
    AccountResponse findByAccNo(String accNo);
}
