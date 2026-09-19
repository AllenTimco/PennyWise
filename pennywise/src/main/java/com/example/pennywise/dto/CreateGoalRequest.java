package com.example.pennywise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateGoalRequest(
        String name,
        BigDecimal targetAmount,
        BigDecimal currentAmount,
        LocalDate targetDate) {
}