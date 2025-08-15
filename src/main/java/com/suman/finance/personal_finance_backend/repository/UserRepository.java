package com.suman.finance.personal_finance_backend.repository;

import com.suman.finance.personal_finance_backend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
