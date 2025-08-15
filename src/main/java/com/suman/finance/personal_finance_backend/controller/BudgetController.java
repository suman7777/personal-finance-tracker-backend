package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.BudgetEntity;
import com.suman.finance.personal_finance_backend.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "http://localhost:3000")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public List<BudgetEntity> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetEntity> getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BudgetEntity createBudget(@RequestBody BudgetEntity budget) {
        return budgetService.saveBudget(budget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetEntity> updateBudget(@PathVariable Long id, @RequestBody BudgetEntity budget) {
        return budgetService.getBudgetById(id)
                .map(existing -> {
                    budget.setId(id);
                    return ResponseEntity.ok(budgetService.saveBudget(budget));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        if (budgetService.getBudgetById(id).isPresent()) {
            budgetService.deleteBudget(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
