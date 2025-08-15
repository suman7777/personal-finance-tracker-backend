package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
