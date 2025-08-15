package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.RecurringPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringPaymentRepository extends JpaRepository<RecurringPaymentEntity, Long> {
}
