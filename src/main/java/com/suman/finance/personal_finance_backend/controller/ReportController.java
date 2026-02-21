package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.ReportEntity;
import com.suman.finance.personal_finance_backend.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // CRUD Operations
    @GetMapping
    public List<ReportEntity> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportEntity> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReportEntity createReport(@RequestBody ReportEntity report) {
        return reportService.saveReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportEntity> updateReport(@PathVariable Long id, @RequestBody ReportEntity report) {
        return reportService.getReportById(id)
                .map(existing -> {
                    report.setId(id);
                    return ResponseEntity.ok(reportService.saveReport(report));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        if (reportService.getReportById(id).isPresent()) {
            reportService.deleteReport(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ENHANCED REPORT ENDPOINTS

    // 1. Income vs Expense Report
    @GetMapping("/analysis/income-expense")
    public ResponseEntity<Map<String, Object>> getIncomeExpenseReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Map<String, Object> report = reportService.getIncomeExpenseReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    // 2. Category-wise Expense Report
    @GetMapping("/analysis/category-wise")
    public ResponseEntity<Map<String, Object>> getCategoryWiseReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Map<String, Object> report = reportService.getCategoryWiseReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    // 3. Budget vs Actual Report
    @GetMapping("/analysis/budget-vs-actual")
    public ResponseEntity<Map<String, Object>> getBudgetVsActualReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Map<String, Object> report = reportService.getBudgetVsActualReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    // 4. Summary Report
    @GetMapping("/analysis/summary")
    public ResponseEntity<Map<String, Object>> getSummaryReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Map<String, Object> report = reportService.getSummaryReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }

    // 5. Recent Reports
    @GetMapping("/recent")
    public ResponseEntity<List<ReportEntity>> getRecentReports(
            @RequestParam(defaultValue = "5") int limit) {
        List<ReportEntity> reports = reportService.getRecentReports(limit);
        return ResponseEntity.ok(reports);
    }

    // 6. Reports by Type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ReportEntity>> getReportsByType(@PathVariable String type) {
        List<ReportEntity> reports = reportService.getReportsByType(type);
        return ResponseEntity.ok(reports);
    }
}
