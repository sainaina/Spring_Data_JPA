package com.example.spring_jpa.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class KYC {
    @Id
    private Integer id;
    @Column(unique = true, nullable = false, length = 12)
    private String nationalCardId;
    @Column(nullable = false)
    private Boolean isVerified;
    @Column(nullable = false)
    private Boolean isDeleted;
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(nullable = false)
    private Customer customer;

}
