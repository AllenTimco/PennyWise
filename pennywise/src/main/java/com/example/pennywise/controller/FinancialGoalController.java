package com.example.pennywise.controller;

import com.example.pennywise.dto.CreateGoalRequest;
import com.example.pennywise.dto.GoalResponse;
import com.example.pennywise.service.FinancialGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "*")
public class FinancialGoalController {

    private final FinancialGoalService goalService;

    public FinancialGoalController(FinancialGoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoalResponse create(@RequestBody CreateGoalRequest request) {
        return goalService.create(request);
    }

    @GetMapping
    public List<GoalResponse> findAll() { return goalService.findAll(); }

    @GetMapping("/{id}")
    public GoalResponse findById(@PathVariable Long id) { return goalService.findById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { goalService.delete(id); }
}