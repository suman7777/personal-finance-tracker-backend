package com.suman.finance.personal_finance_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurring {
	
    private String desc;
    private double amount;
    private String freq;

   
}
