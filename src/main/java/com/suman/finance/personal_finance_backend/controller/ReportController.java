package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.ReportEntity;
import com.suman.finance.personal_finance_backend.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

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
}
