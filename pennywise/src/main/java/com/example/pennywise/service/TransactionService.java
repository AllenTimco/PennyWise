package com.example.pennywise.service;

import com.example.pennywise.dto.CreateTransactionRequest;
import com.example.pennywise.dto.TransactionResponse;
import com.example.pennywise.dto.UpdateTransactionRequest;
import com.example.pennywise.entity.Transaction;
import com.example.pennywise.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse create(CreateTransactionRequest request) {
        validate(request);
        Transaction transaction = new Transaction(
                request.amount(),
                request.description(),
                request.category(),
                request.transactionDate());
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    public List<TransactionResponse> findAll() {
        return transactionRepository.findAllByOrderByTransactionDateDesc()
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    public TransactionResponse findById(Long id) {
        return transactionRepository.findById(id)
                .map(TransactionResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + id));
    }

    public TransactionResponse update(Long id, UpdateTransactionRequest request) {
        validate(request);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + id));
        transaction.setAmount(request.amount());
        transaction.setDescription(request.description());
        transaction.setCategory(request.category());
        transaction.setTransactionDate(request.transactionDate());
        return TransactionResponse.from(transactionRepository.save(transaction));
    }

    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Transaction not found: " + id);
        }
        transactionRepository.deleteById(id);
    }

    private void validate(CreateTransactionRequest request) {
        if (request == null || request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (request.category() == null || request.category().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
        if (request.transactionDate() == null) {
            throw new IllegalArgumentException("Transaction date is required");
        }
    }

    private void validate(UpdateTransactionRequest request) {
        if (request == null || request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (request.category() == null || request.category().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
        if (request.transactionDate() == null) {
            throw new IllegalArgumentException("Transaction date is required");
        }
    }
}