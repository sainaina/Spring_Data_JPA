package com.example.spring_jpa.mapper;

import com.example.spring_jpa.domain.Account;
import com.example.spring_jpa.dto.AccountResponse;
import com.example.spring_jpa.dto.CreateAccountRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(CreateAccountRequest createAccountRequest);

    AccountResponse toAccountResponse(Account account);
}
