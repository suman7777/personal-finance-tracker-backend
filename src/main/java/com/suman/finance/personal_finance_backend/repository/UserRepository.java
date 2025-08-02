package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
