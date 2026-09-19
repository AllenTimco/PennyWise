package com.example.pennywise.dto;

import com.example.pennywise.entity.FinancialProfile;

import java.math.BigDecimal;

public record FinancialProfileResponse(
        BigDecimal monthlyIncome,
        BigDecimal monthlyExpenses,
        BigDecimal totalSavings,
        BigDecimal totalDebt,
        Integer creditScore,
        BigDecimal moneyRemaining,
        BigDecimal savingsRate) {

    public static FinancialProfileResponse from(FinancialProfile profile) {
        BigDecimal remaining = profile.getMonthlyIncome().subtract(profile.getMonthlyExpenses());
        BigDecimal rate = profile.getMonthlyIncome().signum() == 0
                ? BigDecimal.ZERO
                : remaining.multiply(BigDecimal.valueOf(100))
                        .divide(profile.getMonthlyIncome(), 2, java.math.RoundingMode.HALF_UP);
        return new FinancialProfileResponse(profile.getMonthlyIncome(), profile.getMonthlyExpenses(),
                profile.getTotalSavings(), profile.getTotalDebt(), profile.getCreditScore(), remaining, rate);
    }
}