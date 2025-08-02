package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
