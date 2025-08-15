package com.suman.finance.personal_finance_backend.dto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
	
    private List<PieData> pieData;
    private List<LineData> lineData;
    private List<Transaction> transactions;
    private List<Account> accounts;
    private List<Budget> budgets;
    private List<Recurring> recurring;

   
}

