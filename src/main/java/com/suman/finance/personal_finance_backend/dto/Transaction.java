package com.suman.finance.personal_finance_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
    private String date;
    private String category;
    private Double amount;

    
}
