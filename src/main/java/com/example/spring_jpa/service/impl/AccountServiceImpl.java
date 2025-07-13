package com.example.spring_jpa.service.impl;

import com.example.spring_jpa.domain.Account;
import com.example.spring_jpa.dto.AccountResponse;
import com.example.spring_jpa.dto.CreateAccountRequest;
import com.example.spring_jpa.mapper.AccountMapper;
import com.example.spring_jpa.repository.AccountRepository;
import com.example.spring_jpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    //create account
    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        if (accountRepository.existsByAccNo(createAccountRequest.accNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists with actNo: " + createAccountRequest.accNo());
        }
        Account account = accountMapper.toEntity(createAccountRequest);
        account.setIsDeleted(false);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(savedAccount);
    }

    //get account
    @Override
    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();

        return accounts
                .stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    //find by accNo
    @Override
    public AccountResponse findByAccNo(String AccNo) {
        return  accountRepository
                .findByAccNo(AccNo)
                .map(accountMapper::toAccountResponse)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number not found") );
    }



}