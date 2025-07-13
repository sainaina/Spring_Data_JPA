package com.example.spring_jpa.controller;


import com.example.spring_jpa.dto.AccountResponse;
import com.example.spring_jpa.dto.CreateAccountRequest;
import com.example.spring_jpa.dto.CustomerResponse;
import com.example.spring_jpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountResponse createNew(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }
    @GetMapping
    public List<AccountResponse> findAll(){
        return accountService.findAll();
    }
    @GetMapping("/{accNo}")
    public AccountResponse findByAccNo(@PathVariable String accNo){
        return accountService.findByAccNo(accNo);
    }

}
