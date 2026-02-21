package com.suman.finance.personal_finance_backend.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type; // INCOME_EXPENSE, CATEGORY_WISE, BUDGET_COMPARISON, DATE_RANGE, SUMMARY
    private String reportType; // PDF, CSV, etc.
    private LocalDate createdDate;
    private LocalDate startDate;
    private LocalDate endDate;
}
