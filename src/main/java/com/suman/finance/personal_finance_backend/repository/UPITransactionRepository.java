package com.suman.finance.personal_finance_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suman.finance.personal_finance_backend.model.UPITransactionEntity;

public interface UPITransactionRepository extends JpaRepository<UPITransactionEntity, Integer> {

}