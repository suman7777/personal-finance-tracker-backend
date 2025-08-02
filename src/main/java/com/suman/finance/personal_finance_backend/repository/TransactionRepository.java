package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
