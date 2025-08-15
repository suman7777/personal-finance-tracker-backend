package com.suman.finance.personal_finance_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineData {

	private String month;
	private int income;
	private int expense;

}
