package com.suman.finance.personal_finance_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String category;
    private Double amount;
    private String type; // Income or Expense
    private String notes;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

   
}
