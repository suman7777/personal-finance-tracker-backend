package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
}
