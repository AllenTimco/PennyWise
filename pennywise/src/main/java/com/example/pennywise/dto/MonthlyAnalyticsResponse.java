package com.example.pennywise.dto;

import java.math.BigDecimal;
import java.util.List;

public record MonthlyAnalyticsResponse(
        int year,
        int month,
        BigDecimal totalExpenses,
        List<CategoryTotalResponse> categories) {
}