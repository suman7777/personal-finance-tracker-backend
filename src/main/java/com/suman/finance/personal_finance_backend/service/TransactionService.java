package com.suman.finance.personal_finance_backend.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suman.finance.personal_finance_backend.model.TransactionEntity;
import com.suman.finance.personal_finance_backend.model.UPITransactionEntity;
import com.suman.finance.personal_finance_backend.repository.TransactionRepository;
import com.suman.finance.personal_finance_backend.repository.UPITransactionRepository;
import com.suman.finance.personal_finance_backend.utility.ExcelHelper;

@Service
public class TransactionService {
	
	@Autowired
    private UPITransactionRepository repository;


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
    
    public void saveExcelData(MultipartFile file) {
        try {
            List<UPITransactionEntity> transactions = ExcelHelper.parseExcel(file.getInputStream());
            repository.saveAll(transactions);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file", e);
        }
    }
    
    public List<TransactionEntity> searchTransactions(String category, String type, String description, 
                                                      String notes, LocalDate startDate, LocalDate endDate, 
                                                      Double minAmount, Double maxAmount) {
        return transactionRepository.searchTransactions(category, type, description, notes, startDate, endDate, minAmount, maxAmount);
    }
}