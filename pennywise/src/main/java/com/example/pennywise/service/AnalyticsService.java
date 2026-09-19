package com.example.pennywise.service;

import com.example.pennywise.dto.CategoryTotalResponse;
import com.example.pennywise.dto.MonthlyAnalyticsResponse;
import com.example.pennywise.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final TransactionRepository transactionRepository;

    public AnalyticsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public MonthlyAnalyticsResponse monthly(int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        var transactions = transactionRepository.findByTransactionDateBetween(start, end);

        Map<String, BigDecimal> totals = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getCategory(),
                        Collectors.reducing(BigDecimal.ZERO, transaction -> transaction.getAmount(), BigDecimal::add)));

        var categories = totals.entrySet().stream()
                .map(entry -> new CategoryTotalResponse(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CategoryTotalResponse::amount).reversed())
                .toList();

        return new MonthlyAnalyticsResponse(
                year,
                month,
                categories.stream().map(CategoryTotalResponse::amount).reduce(BigDecimal.ZERO, BigDecimal::add),
                categories);
    }
}