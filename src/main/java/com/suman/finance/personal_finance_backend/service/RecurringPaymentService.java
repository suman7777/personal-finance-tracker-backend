package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.RecurringPaymentEntity;
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

    public List<RecurringPaymentEntity> getAllRecurringPayments() {
        return recurringPaymentRepository.findAll();
    }

    public Optional<RecurringPaymentEntity> getRecurringPaymentById(Long id) {
        return recurringPaymentRepository.findById(id);
    }

    public RecurringPaymentEntity saveRecurringPayment(RecurringPaymentEntity payment) {
        return recurringPaymentRepository.save(payment);
    }

    public void deleteRecurringPayment(Long id) {
        recurringPaymentRepository.deleteById(id);
    }
}
