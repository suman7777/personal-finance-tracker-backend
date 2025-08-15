package com.suman.finance.personal_finance_backend.controller;

import com.suman.finance.personal_finance_backend.model.AccountEntity;
import com.suman.finance.personal_finance_backend.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountEntity> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account) {
        return accountService.saveAccount(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Long id, @RequestBody AccountEntity account) {
        return accountService.getAccountById(id)
                .map(existing -> {
                    account.setId(id);
                    return ResponseEntity.ok(accountService.saveAccount(account));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (accountService.getAccountById(id).isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
