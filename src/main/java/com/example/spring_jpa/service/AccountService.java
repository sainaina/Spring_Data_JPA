package com.example.spring_jpa.service;

import com.example.spring_jpa.dto.*;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAll();

    AccountResponse findByAccNo(String accNo);

    void deleteByAccNo(String accNo);

    AccountResponse updateAccountByAccNo(String accNo, UpdateAccountRequest updateRequest);
    void disableAccountByAccNo(String accNo);

}
