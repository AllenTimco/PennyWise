package com.example.pennywise.repository;

import com.example.pennywise.entity.FinancialGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Long> {
}