package com.example.spring_jpa.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String actNo;

    @Column(nullable = false, length = 50)
    private String actName;

    @Column(nullable = false, length = 15)
    private String actCurrency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isHide;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(nullable = false, name = "cust_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    private AccountType accountType;
}
