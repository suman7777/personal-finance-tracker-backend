package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.RecurringPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringPaymentRepository extends JpaRepository<RecurringPayment, Long> {
}
