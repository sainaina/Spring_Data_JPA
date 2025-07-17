package com.example.spring_jpa.mapper;

import com.example.spring_jpa.domain.Customer;
import com.example.spring_jpa.dto.CreateCustomerRequest;
import com.example.spring_jpa.dto.CustomerResponse;
import com.example.spring_jpa.dto.UpdateCustomerRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "customerSegment", ignore = true)
    Customer fromCreateCustomerRequest(CreateCustomerRequest createCustomerRequest);
}
