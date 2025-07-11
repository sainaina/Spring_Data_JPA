package com.example.spring_jpa.service.impl;

import com.example.spring_jpa.domain.Customer;
import com.example.spring_jpa.dto.CreateCustomerRequest;
import com.example.spring_jpa.dto.CustomerResponse;
import com.example.spring_jpa.dto.UpdateCustomerRequest;
import com.example.spring_jpa.mapper.CustomerMapper;
import com.example.spring_jpa.repository.CustomerRepository;
import com.example.spring_jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumber(phoneNumber)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number not found") );
    }


//    update
    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number not found") );

        customerMapper.toCustomerPartially(updateCustomerRequest, customer);
        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if (customerRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }


        Customer customer = customerMapper.fromCreateCustomerRequest(request);
        customer.setIsDeleted(false);
        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List <Customer> customers = customerRepository.findAll();
        return customers
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }
}
