package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.TransactionEntity;
import com.suman.finance.personal_finance_backend.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionEntity> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
