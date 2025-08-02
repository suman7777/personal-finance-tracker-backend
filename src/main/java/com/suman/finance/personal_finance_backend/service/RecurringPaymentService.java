package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.RecurringPayment;
import com.suman.finance.personal_finance_backend.repository.RecurringPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecurringPaymentService {
    private final RecurringPaymentRepository recurringPaymentRepository;

    public RecurringPaymentService(RecurringPaymentRepository recurringPaymentRepository) {
        this.recurringPaymentRepository = recurringPaymentRepository;
    }

    public List<RecurringPayment> getAllRecurringPayments() {
        return recurringPaymentRepository.findAll();
    }

    public Optional<RecurringPayment> getRecurringPaymentById(Long id) {
        return recurringPaymentRepository.findById(id);
    }

    public RecurringPayment saveRecurringPayment(RecurringPayment payment) {
        return recurringPaymentRepository.save(payment);
    }

    public void deleteRecurringPayment(Long id) {
        recurringPaymentRepository.deleteById(id);
    }
}
