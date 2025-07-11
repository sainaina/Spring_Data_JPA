package com.example.spring_jpa.service;

import com.example.spring_jpa.dto.CreateCustomerRequest;
import com.example.spring_jpa.dto.CustomerResponse;
import com.example.spring_jpa.dto.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {
        CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);
        List<CustomerResponse> findAll();
        CustomerResponse findByPhoneNumber(String phoneNumber);
        CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest  updateCustomerRequest);
}
