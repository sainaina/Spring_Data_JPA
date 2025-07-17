package com.example.spring_jpa.service.impl;

import com.example.spring_jpa.domain.Account;
import com.example.spring_jpa.domain.AccountType;
import com.example.spring_jpa.domain.Customer;
import com.example.spring_jpa.dto.*;
import com.example.spring_jpa.mapper.AccountMapper;
import com.example.spring_jpa.repository.AccountRepository;
import com.example.spring_jpa.repository.AccountTypeRepository;
import com.example.spring_jpa.repository.CustomerRepository;
import com.example.spring_jpa.service.AccountService;
import com.example.spring_jpa.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    //create
    @Override
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        Account account = new Account();


        AccountType accountType = accountTypeRepository
                .findByType(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account Type Not Found"));


        Customer customer = customerRepository
                .findByPhoneNumber(createAccountRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));

        switch (createAccountRequest.actCurrency()) {
            case CurrencyUtil.USD -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(10)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 10 USD");
                }

                if (customer.getCustomerSegment().getSegment().equals("REGULAR")) {
                    account.setOverLimit(BigDecimal.valueOf(5000));
                } else if (customer.getCustomerSegment().getSegment().equals("SILVER")) {
                    account.setOverLimit(BigDecimal.valueOf(10000));
                } else {
                    account.setOverLimit(BigDecimal.valueOf(50000));
                }
            }
            case CurrencyUtil.KHR -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 40,000 KHR");
                }

                if (customer.getCustomerSegment().getSegment().equals("REGULAR")) {
                    account.setOverLimit(BigDecimal.valueOf(5000 * 4000));
                } else if (customer.getCustomerSegment().getSegment().equals("SILVER")) {
                    account.setOverLimit(BigDecimal.valueOf(10000 * 4000));
                } else {
                    account.setOverLimit(BigDecimal.valueOf(50000 * 4000));
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Currency is not supported");
        }


        if (createAccountRequest.actNo() != null) {
            if (accountRepository.existsByActNo(createAccountRequest.actNo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Account with Act No %s already exists", createAccountRequest.actNo()));
            }
            account.setActNo(createAccountRequest.actNo());
        } else {
            String actNo;
            do {
                actNo = String.format("%09d", new Random().nextInt(1_000_000_000)); // Max: 999,999,999
            } while (accountRepository.existsByActNo(actNo));
            account.setActNo(actNo);
        }

        account.setActName(createAccountRequest.actName());
        account.setActCurrency(createAccountRequest.actCurrency().name());
        account.setBalance(createAccountRequest.balance());
        account.setIsHide(false);
        account.setIsDeleted(false);
        account.setCustomer(customer);
        account.setAccountType(accountType);

        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
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
    public AccountResponse findByAccNo(String ActNo) {
        return  accountRepository
                .findByActNo(ActNo)
                .map(accountMapper::toAccountResponse)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Number not found") );
    }
    //delete acc
    @Override
    public void deleteByAccNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountRepository.delete(account);
    }

    //update acc
    @Override
    public AccountResponse updateAccountByAccNo(String accNo, UpdateAccountRequest updateRequest) {
        Account account = accountRepository.findByActNo(accNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountMapper.toAccountPartially(updateRequest, account);
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toAccountResponse(updatedAccount);
    }

    @Override
    public void disableAccountByAccNo(String accNo) {
        Account account = accountRepository.findByActNo(accNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        account.setIsDeleted(true);
        accountRepository.save(account);
    }

}