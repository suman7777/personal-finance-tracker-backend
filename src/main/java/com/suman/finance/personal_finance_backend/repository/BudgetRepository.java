package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
