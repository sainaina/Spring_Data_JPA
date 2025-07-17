package com.example.spring_jpa.init;

import com.example.spring_jpa.domain.CustomerSegment;
import com.example.spring_jpa.repository.CustomerRepository;
import com.example.spring_jpa.repository.CustomerSegmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {
    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init() {
        if (customerSegmentRepository.count() == 0) {
            CustomerSegment regular = new CustomerSegment();
            regular.setSegment("REGULAR");
            regular.setIsDeleted(false);
            regular.setDescription("Regular segment");

            CustomerSegment silver = new CustomerSegment();
            silver.setSegment("SILVER");
            silver.setIsDeleted(false);
            silver.setDescription("Silver segment");

            CustomerSegment gold = new CustomerSegment();
            gold.setSegment("GOLD");
            gold.setIsDeleted(false);
            gold.setDescription("Gold segment");

            customerSegmentRepository.saveAll(List.of(regular, silver, gold));
        }
    }
}
