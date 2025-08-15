package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.AccountEntity;
import com.suman.finance.personal_finance_backend.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountEntity> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public AccountEntity saveAccount(AccountEntity account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
