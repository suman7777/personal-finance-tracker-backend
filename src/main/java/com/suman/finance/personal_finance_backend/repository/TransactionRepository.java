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
    
    // Search transactions with filters
    @Query("SELECT t FROM TransactionEntity t WHERE " +
           "(:category IS NULL OR t.category = :category) AND " +
           "(:type IS NULL OR t.type = :type) AND " +
           "(:description IS NULL OR LOWER(t.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND " +
           "(:notes IS NULL OR LOWER(t.notes) LIKE LOWER(CONCAT('%', :notes, '%'))) AND " +
           "(:startDate IS NULL OR t.date >= :startDate) AND " +
           "(:endDate IS NULL OR t.date <= :endDate) AND " +
           "(:minAmount IS NULL OR t.amount >= :minAmount) AND " +
           "(:maxAmount IS NULL OR t.amount <= :maxAmount) " +
           "ORDER BY t.date DESC")
    List<TransactionEntity> searchTransactions(
            @Param("category") String category,
            @Param("type") String type,
            @Param("description") String description,
            @Param("notes") String notes,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount
    );
}
