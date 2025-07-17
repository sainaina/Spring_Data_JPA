package com.example.spring_jpa.mapper;

import com.example.spring_jpa.domain.Account;
import com.example.spring_jpa.domain.AccountType;
import com.example.spring_jpa.dto.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toEntity(CreateAccountRequest createAccountRequest);

    @Mapping(source = "accountType.type", target = "accountType")
    AccountResponse toAccountResponse(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateRequest, @MappingTarget Account account);

    default AccountType map(String type) {
        if (type == null) return null;
        AccountType accountType = new AccountType();
        accountType.setType(type);
        return accountType;
    }
}
