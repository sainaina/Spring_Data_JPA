package com.example.spring_jpa.controller;


import com.example.spring_jpa.dto.*;
import com.example.spring_jpa.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

@ResponseStatus(HttpStatus.CREATED)
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


    @DeleteMapping("/{actNo}")
    public void deleteAccount(@PathVariable String actNo) {
        accountService.deleteByAccNo(actNo);
    }

    @PatchMapping("/{accNo}")
    public AccountResponse updateAccount(
            @PathVariable String accNo,
            @RequestBody UpdateAccountRequest request) {
        return accountService.updateAccountByAccNo(accNo,request);
    }


    @PutMapping("/{accNo}/disable")
    public ResponseEntity<Void> disableAccount(@PathVariable String accNo) {
        accountService.disableAccountByAccNo(accNo);
        return ResponseEntity.noContent().build();
    }
}