package com.suman.finance.personal_finance_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RecurringPayment")
public class RecurringPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private String frequency; // e.g. Monthly, Weekly

 
}
