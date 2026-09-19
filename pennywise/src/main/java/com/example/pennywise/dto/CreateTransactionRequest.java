package com.example.pennywise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(
        BigDecimal amount,
        String description,
        String category,
        LocalDate transactionDate) {
}