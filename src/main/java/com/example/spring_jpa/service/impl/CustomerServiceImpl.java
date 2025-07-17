package com.example.spring_jpa.service.impl;

import com.example.spring_jpa.domain.Customer;
import com.example.spring_jpa.domain.CustomerSegment;
import com.example.spring_jpa.domain.KYC;
import com.example.spring_jpa.dto.CreateCustomerRequest;
import com.example.spring_jpa.dto.CustomerResponse;
import com.example.spring_jpa.dto.UpdateCustomerRequest;
import com.example.spring_jpa.mapper.CustomerMapper;
import com.example.spring_jpa.repository.CustomerRepository;
import com.example.spring_jpa.repository.CustomerSegmentRepository;
import com.example.spring_jpa.repository.KYCRepository;
import com.example.spring_jpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KYCRepository kycRepository;
    private final CustomerSegmentRepository customerSegmentRepository;

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number not found"));
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone Number not found"));

        customerMapper.toCustomerPartially(updateCustomerRequest, customer);
        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Transactional
    @Override
    public void disableAccountByPhoneNumber(String phoneNumber) {
        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Phone Number not found");
        }
        customerRepository.disableAccountByPhoneNumber(phoneNumber);
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        if (customerRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number already exists"
            );
        }

        if (kycRepository.existsByNationalCardId(request.nationalCardId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card ID already exists"
            );
        }
        CustomerSegment customerSegment = customerSegmentRepository
                .findBySegment(request.customerSegment())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer segment not found"));

        Customer customer = customerMapper.fromCreateCustomerRequest(request);
        customer.setCustomerSegment(customerSegment);
        customer.setIsDeleted(false);

        KYC kyc = new KYC();
        kyc.setCustomer(customer);
        kyc.setNationalCardId(request.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        customer.setKyc(kyc);
        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }


    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository.findAllByIsDeletedFalse()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }
}
