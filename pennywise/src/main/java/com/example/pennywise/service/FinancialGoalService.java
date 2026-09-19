package com.example.pennywise.service;

import com.example.pennywise.dto.CreateGoalRequest;
import com.example.pennywise.dto.GoalResponse;
import com.example.pennywise.entity.FinancialGoal;
import com.example.pennywise.repository.FinancialGoalRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialGoalService {

    private final FinancialGoalRepository goalRepository;

    public FinancialGoalService(FinancialGoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public GoalResponse create(CreateGoalRequest request) {
        validate(request);
        return GoalResponse.from(goalRepository.save(new FinancialGoal(
                request.name(), request.targetAmount(), request.currentAmount(), request.targetDate())));
    }

    public List<GoalResponse> findAll() {
        return goalRepository.findAll().stream().map(GoalResponse::from).toList();
    }

    public GoalResponse findById(Long id) {
        return goalRepository.findById(id).map(GoalResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found: " + id));
    }

    public void delete(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new IllegalArgumentException("Goal not found: " + id);
        }
        goalRepository.deleteById(id);
    }

    private void validate(CreateGoalRequest request) {
        if (request == null || request.name() == null || request.name().isBlank()) {
            throw new IllegalArgumentException("Goal name is required");
        }
        if (request.targetAmount() == null || request.targetAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Target amount must be greater than zero");
        }
        if (request.currentAmount() == null || request.currentAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Current amount cannot be negative");
        }
    }
}