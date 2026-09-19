package com.example.pennywise.dto;

import java.math.BigDecimal;

public record FinancialProfileRequest(
        BigDecimal monthlyIncome,
        BigDecimal monthlyExpenses,
        BigDecimal totalSavings,
        BigDecimal totalDebt,
        Integer creditScore) {
}