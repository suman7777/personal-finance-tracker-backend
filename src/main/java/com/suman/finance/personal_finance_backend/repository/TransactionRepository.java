package com.suman.finance.personal_finance_backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.suman.finance.personal_finance_backend.model.TransactionEntity;



public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	
	// Expenses for current month
    @Query("SELECT t FROM TransactionEntity t WHERE t.type = :type AND t.date BETWEEN :startDate AND :endDate")
    List<TransactionEntity> findByTypeAndDateRange(@Param("type") String type,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate
    );


    // Latest N transactions - needs @Query because Spring Data doesn't support dynamic limit directly
    @Query(value = "SELECT * FROM Transaction ORDER BY date DESC LIMIT :limit", nativeQuery = true)
    List<TransactionEntity> findTopNByOrderByDateDesc(@Param("limit") int limit);
}
