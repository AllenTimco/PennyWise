package com.example.pennywise.dto;

import com.example.pennywise.entity.FinancialGoal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalResponse(
        Long id,
        String name,
        BigDecimal targetAmount,
        BigDecimal currentAmount,
        LocalDate targetDate,
        BigDecimal progressPercentage) {

    public static GoalResponse from(FinancialGoal goal) {
        BigDecimal progress = goal.getTargetAmount().signum() == 0
                ? BigDecimal.ZERO
                : goal.getCurrentAmount()
                        .multiply(BigDecimal.valueOf(100))
                        .divide(goal.getTargetAmount(), 2, java.math.RoundingMode.HALF_UP);
        return new GoalResponse(goal.getId(), goal.getName(), goal.getTargetAmount(),
                goal.getCurrentAmount(), goal.getTargetDate(), progress.min(BigDecimal.valueOf(100)));
    }
}