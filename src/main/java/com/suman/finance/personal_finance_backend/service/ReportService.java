package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.ReportEntity;
import com.suman.finance.personal_finance_backend.model.TransactionEntity;
import com.suman.finance.personal_finance_backend.model.BudgetEntity;
import com.suman.finance.personal_finance_backend.repository.ReportRepository;
import com.suman.finance.personal_finance_backend.repository.TransactionRepository;
import com.suman.finance.personal_finance_backend.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final TransactionRepository transactionRepository;
    private final BudgetRepository budgetRepository;

    public ReportService(ReportRepository reportRepository, TransactionRepository transactionRepository, BudgetRepository budgetRepository) {
        this.reportRepository = reportRepository;
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
    }

    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<ReportEntity> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public ReportEntity saveReport(ReportEntity report) {
        report.setCreatedDate(LocalDate.now());
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    // REPORT GENERATION METHODS
    
    // 1. Income vs Expense Report - Monthly Breakdown
    public Map<String, Object> getIncomeExpenseReport(LocalDate startDate, LocalDate endDate) {
        List<TransactionEntity> transactions = transactionRepository.findAll().stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        Map<String, Map<String, Double>> monthlyData = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getYear() + "-" + String.format("%02d", t.getDate().getMonthValue()),
                        Collectors.groupingBy(
                                TransactionEntity::getType,
                                Collectors.summingDouble(TransactionEntity::getAmount)
                        )
                ));

        Double totalIncome = transactions.stream()
                .filter(t -> "INCOME".equals(t.getType()))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        Double totalExpense = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        Map<String, Object> report = new HashMap<>();
        report.put("monthlyBreakdown", monthlyData);
        report.put("totalIncome", totalIncome);
        report.put("totalExpense", totalExpense);
        report.put("netBalance", totalIncome - totalExpense);
        return report;
    }

    // 2. Category-wise Expense Report
    public Map<String, Object> getCategoryWiseReport(LocalDate startDate, LocalDate endDate) {
        List<TransactionEntity> expenses = transactionRepository.findAll().stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        Map<String, Double> categoryTotals = expenses.stream()
                .collect(Collectors.groupingBy(
                        TransactionEntity::getCategory,
                        Collectors.summingDouble(TransactionEntity::getAmount)
                ));

        Double totalExpenses = categoryTotals.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        Map<String, Double> categoryPercentage = new HashMap<>();
        categoryTotals.forEach((category, amount) -> {
            Double percentage = (amount / totalExpenses) * 100;
            categoryPercentage.put(category, Math.round(percentage * 100.0) / 100.0);
        });

        Map<String, Object> report = new HashMap<>();
        report.put("categoryExpenses", categoryTotals);
        report.put("categoryPercentage", categoryPercentage);
        report.put("totalExpenses", totalExpenses);
        return report;
    }

    // 3. Budget vs Actual Report
    public Map<String, Object> getBudgetVsActualReport(LocalDate startDate, LocalDate endDate) {
        List<BudgetEntity> budgets = budgetRepository.findAll();
        List<TransactionEntity> transactions = transactionRepository.findAll().stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        Map<String, Double> actualByCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        TransactionEntity::getCategory,
                        Collectors.summingDouble(TransactionEntity::getAmount)
                ));

        List<Map<String, Object>> budgetAnalysis = new ArrayList<>();
        for (BudgetEntity budget : budgets) {
            Map<String, Object> analysis = new HashMap<>();
            Double actual = actualByCategory.getOrDefault(budget.getCategory(), 0.0);
            Double budgeted = budget.getAmount();
            Double variance = budgeted - actual;
            Double variancePercentage = (variance / budgeted) * 100;

            analysis.put("category", budget.getCategory());
            analysis.put("budgeted", budgeted);
            analysis.put("actual", actual);
            analysis.put("variance", Math.round(variance * 100.0) / 100.0);
            analysis.put("variancePercentage", Math.round(variancePercentage * 100.0) / 100.0);
            analysis.put("status", variance >= 0 ? "Under Budget" : "Over Budget");

            budgetAnalysis.add(analysis);
        }

        Map<String, Object> report = new HashMap<>();
        report.put("budgetAnalysis", budgetAnalysis);
        return report;
    }

    // 4. Summary Report - Financial Overview
    public Map<String, Object> getSummaryReport(LocalDate startDate, LocalDate endDate) {
        List<TransactionEntity> transactions = transactionRepository.findAll().stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        Double totalIncome = transactions.stream()
                .filter(t -> "INCOME".equals(t.getType()))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        Double totalExpense = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .mapToDouble(TransactionEntity::getAmount)
                .sum();

        Double netBalance = totalIncome - totalExpense;

        Map<String, Double> topExpenseCategories = transactions.stream()
                .filter(t -> "EXPENSE".equals(t.getType()))
                .collect(Collectors.groupingBy(
                        TransactionEntity::getCategory,
                        Collectors.summingDouble(TransactionEntity::getAmount)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toLinkedHashMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        Double avgTransactionAmount = transactions.isEmpty() ? 0.0 : 
                transactions.stream().mapToDouble(TransactionEntity::getAmount).average().orElse(0.0);

        Map<String, Object> report = new HashMap<>();
        report.put("startDate", startDate);
        report.put("endDate", endDate);
        report.put("totalIncome", totalIncome);
        report.put("totalExpense", totalExpense);
        report.put("netBalance", netBalance);
        report.put("transactionCount", transactions.size());
        report.put("averageTransactionAmount", Math.round(avgTransactionAmount * 100.0) / 100.0);
        report.put("topExpenseCategories", topExpenseCategories);
        return report;
    }

    // 5. Get Recent Reports
    public List<ReportEntity> getRecentReports(int limit) {
        return reportRepository.findRecentReports(limit);
    }

    // 6. Get Reports by Type
    public List<ReportEntity> getReportsByType(String type) {
        return reportRepository.findByType(type);
    }
}
