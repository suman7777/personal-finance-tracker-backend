package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.RecurringPaymentEntity;
import com.suman.finance.personal_finance_backend.service.RecurringPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-payments")
@CrossOrigin(origins = "http://localhost:3000")
public class RecurringPaymentController {
    private final RecurringPaymentService recurringPaymentService;

    public RecurringPaymentController(RecurringPaymentService recurringPaymentService) {
        this.recurringPaymentService = recurringPaymentService;
    }

    @GetMapping
    public List<RecurringPaymentEntity> getAllRecurringPayments() {
        return recurringPaymentService.getAllRecurringPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecurringPaymentEntity> getRecurringPaymentById(@PathVariable Long id) {
        return recurringPaymentService.getRecurringPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RecurringPaymentEntity createRecurringPayment(@RequestBody RecurringPaymentEntity payment) {
        return recurringPaymentService.saveRecurringPayment(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecurringPaymentEntity> updateRecurringPayment(@PathVariable Long id, @RequestBody RecurringPaymentEntity payment) {
        return recurringPaymentService.getRecurringPaymentById(id)
                .map(existing -> {
                    payment.setId(id);
                    return ResponseEntity.ok(recurringPaymentService.saveRecurringPayment(payment));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecurringPayment(@PathVariable Long id) {
        if (recurringPaymentService.getRecurringPaymentById(id).isPresent()) {
            recurringPaymentService.deleteRecurringPayment(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
