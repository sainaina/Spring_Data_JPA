package com.example.spring_jpa.controller;

import com.example.spring_jpa.dto.*;
import com.example.spring_jpa.service.AccountService;
import com.example.spring_jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;

    @GetMapping("/{phoneNumber}")
    public CustomerResponse getCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @PatchMapping("/{phoneNumber}")
    public CustomerResponse updateCustomer(@PathVariable String phoneNumber, @RequestBody UpdateCustomerRequest updateCustomerRequest){
        return customerService.updateByPhoneNumber(phoneNumber,updateCustomerRequest);
    }

    @GetMapping
    public List<CustomerResponse> findAll(){
        return customerService.findAll();
    }
    @PostMapping
    public CustomerResponse createNew(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createNew(createCustomerRequest);
    }
}

