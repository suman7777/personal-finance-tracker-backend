package com.suman.finance.personal_finance_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
    private String name;
    private String type;
    private Double amount;

    
}
