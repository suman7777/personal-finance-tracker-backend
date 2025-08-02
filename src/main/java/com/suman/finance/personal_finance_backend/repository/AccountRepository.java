package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
