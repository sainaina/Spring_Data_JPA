package com.example.spring_jpa.init;

import com.example.spring_jpa.domain.AccountType;
import com.example.spring_jpa.repository.AccountTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class AccountTypeInitialize {
    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init() {
        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setType("PAYROLL");
            payroll.setIsDeleted(false);

            AccountType saving = new AccountType();
            saving.setType("SAVING");
            saving.setIsDeleted(false);

            AccountType junior = new AccountType();
            junior.setType("JUNIOR");
            junior.setIsDeleted(false);

            accountTypeRepository.saveAll(List.of(payroll, saving, junior));
        }
    }
}
