package com.example.spring_jpa.mapper;

import com.example.spring_jpa.domain.Account;
import com.example.spring_jpa.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(CreateAccountRequest createAccountRequest);

    AccountResponse toAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateRequest, @MappingTarget Account account);
}

