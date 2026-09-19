package com.example.pennywise.controller;

import com.example.pennywise.dto.MonthlyAnalyticsResponse;
import com.example.pennywise.service.AnalyticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/monthly")
    public MonthlyAnalyticsResponse monthly(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        LocalDate today = LocalDate.now();
        return analyticsService.monthly(
                year == null ? today.getYear() : year,
                month == null ? today.getMonthValue() : month);
    }
}