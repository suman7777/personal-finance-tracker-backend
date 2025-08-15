package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.ReportEntity;
import com.suman.finance.personal_finance_backend.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<ReportEntity> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public ReportEntity saveReport(ReportEntity report) {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
