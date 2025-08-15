package com.suman.finance.personal_finance_backend.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.suman.finance.personal_finance_backend.dto.Account;
import com.suman.finance.personal_finance_backend.dto.Budget;
import com.suman.finance.personal_finance_backend.dto.DashboardResponse;
import com.suman.finance.personal_finance_backend.dto.LineData;
import com.suman.finance.personal_finance_backend.dto.PieData;
import com.suman.finance.personal_finance_backend.dto.Recurring;
import com.suman.finance.personal_finance_backend.dto.Transaction;
import com.suman.finance.personal_finance_backend.model.AccountEntity;
import com.suman.finance.personal_finance_backend.model.BudgetEntity;
import com.suman.finance.personal_finance_backend.model.TransactionEntity;
import com.suman.finance.personal_finance_backend.repository.AccountRepository;
import com.suman.finance.personal_finance_backend.repository.BudgetRepository;
import com.suman.finance.personal_finance_backend.repository.RecurringPaymentRepository;
import com.suman.finance.personal_finance_backend.repository.TransactionRepository;

@Service
public class DashboardService {

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final BudgetRepository budgetRepo;
    private final RecurringPaymentRepository recurringRepo;

    public DashboardService(TransactionRepository transactionRepo,
                            AccountRepository accountRepo,
                            BudgetRepository budgetRepo,
                            RecurringPaymentRepository recurringRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.budgetRepo = budgetRepo;
        this.recurringRepo = recurringRepo;
    }

    // MAIN ENTRY POINT
    public DashboardResponse getDashboardData() {
        DashboardResponse response = new DashboardResponse();
        response.setPieData(getPieData());
        response.setLineData(getLineData());
        response.setTransactions(getRecentTransactions(5));
        response.setAccounts(getAccounts());
        response.setBudgets(getBudgets());
        response.setRecurring(getRecurringPayments());
        return response;
    }

    // 1️⃣ Pie Data - Expense by Category (Current Month)
    private List<PieData> getPieData() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

		List<TransactionEntity> expenses = transactionRepo.findByTypeAndDateRange("EXPENSE", startOfMonth, endOfMonth);

		Map<String, Double> categoryTotals = expenses.stream().collect(Collectors
													 .groupingBy(TransactionEntity::getCategory, Collectors.summingDouble(TransactionEntity::getAmount)));

		return categoryTotals.entrySet().stream().map(e -> new PieData(e.getKey(), e.getValue().intValue()))
												 .collect(Collectors.toList());
    }


    // 2️⃣ Line Data - Monthly Totals
    private List<LineData> getLineData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        List<TransactionEntity> allTransactions = transactionRepo.findAll();

        Map<String, Map<String, Double>> monthlyTotals = allTransactions.stream()
                .collect(Collectors.groupingBy(t -> t.getDate().format(formatter), // format LocalDate → "YYYY-MM"
                         Collectors.groupingBy(TransactionEntity::getType,Collectors.summingDouble(TransactionEntity::getAmount)
                        )
                ));

        return monthlyTotals.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new LineData(
                        entry.getKey(),
                        entry.getValue().getOrDefault("INCOME", 0.0).intValue(),
                        entry.getValue().getOrDefault("EXPENSE", 0.0).intValue()
                ))
                .collect(Collectors.toList());
    }


    // 3️⃣ Recent Transactions
    private List<Transaction> getRecentTransactions(int limit) {
        return transactionRepo.findTopNByOrderByDateDesc(limit).stream()
                .map(t -> new Transaction(t.getDate().toString(), t.getCategory(), t.getAmount()))
                .collect(Collectors.toList());
    }

    // 4️⃣ Accounts
    private List<Account> getAccounts() {
        return accountRepo.findAll().stream()
                .map(a -> new Account(a.getName(), a.getType(), a.getBalance()))
                .collect(Collectors.toList());
    }

    // 5️⃣ Budgets
    private List<Budget> getBudgets() {
        return budgetRepo.findAll().stream()
                .map(b -> new Budget(
                        b.getCategory(),
                        calculateBudgetPercent(b.getSpentAmount(), b.getLimitAmount())
                ))
                .collect(Collectors.toList());
    }

    // 6️⃣ Recurring Payments
    private List<Recurring> getRecurringPayments() {
        return recurringRepo.findAll().stream()
                .map(r -> new Recurring(r.getDescription(), r.getAmount(), r.getFrequency()))
                .collect(Collectors.toList());
    }
    
    private int calculateBudgetPercent(Double spent, Double limit) {
        if (limit == null || limit == 0) {
            return 0; // Avoid division by zero
        }
        if (spent == null) {
            spent = 0.0;
        }
        return (int) Math.round((spent / limit) * 100);
    }
}