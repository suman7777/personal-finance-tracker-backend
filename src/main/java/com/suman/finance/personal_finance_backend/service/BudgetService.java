package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.BudgetEntity;
import com.suman.finance.personal_finance_backend.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<BudgetEntity> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<BudgetEntity> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public BudgetEntity saveBudget(BudgetEntity budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
