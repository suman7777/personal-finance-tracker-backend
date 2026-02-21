package com.suman.finance.personal_finance_backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "upi_transactions")
public class UPITransactionEntity {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private LocalDate txnDate;
	    private LocalTime txnTime;
	    private String bankName;
	    private String accountNumber;
	    private String sender;
	    private String receiver;
	    private String paymentReference;
	    private String payCollect;
	    private BigDecimal amount;
	    private String drCr;
	    private String status;
	    
}
