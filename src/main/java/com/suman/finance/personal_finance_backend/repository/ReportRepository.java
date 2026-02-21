package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    
    // Find reports by type
    List<ReportEntity> findByType(String type);
    
    // Find reports by date range
    @Query("SELECT r FROM ReportEntity r WHERE r.createdDate BETWEEN :startDate AND :endDate ORDER BY r.createdDate DESC")
    List<ReportEntity> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    // Find recent reports
    @Query(value = "SELECT * FROM Report ORDER BY created_date DESC LIMIT :limit", nativeQuery = true)
    List<ReportEntity> findRecentReports(@Param("limit") int limit);
}
