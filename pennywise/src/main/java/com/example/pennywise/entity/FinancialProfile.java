package com.example.pennywise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "financial_profiles")
public class FinancialProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpenses;
    private BigDecimal totalSavings;
    private BigDecimal totalDebt;
    private Integer creditScore;

    protected FinancialProfile() { }

    public FinancialProfile(BigDecimal monthlyIncome, BigDecimal monthlyExpenses, BigDecimal totalSavings,
            BigDecimal totalDebt, Integer creditScore) {
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenses = monthlyExpenses;
        this.totalSavings = totalSavings;
        this.totalDebt = totalDebt;
        this.creditScore = creditScore;
    }

    public Long getId() { return id; }
    public BigDecimal getMonthlyIncome() { return monthlyIncome; }
    public BigDecimal getMonthlyExpenses() { return monthlyExpenses; }
    public BigDecimal getTotalSavings() { return totalSavings; }
    public BigDecimal getTotalDebt() { return totalDebt; }
    public Integer getCreditScore() { return creditScore; }
    public void setMonthlyIncome(BigDecimal monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public void setMonthlyExpenses(BigDecimal monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }
    public void setTotalSavings(BigDecimal totalSavings) { this.totalSavings = totalSavings; }
    public void setTotalDebt(BigDecimal totalDebt) { this.totalDebt = totalDebt; }
    public void setCreditScore(Integer creditScore) { this.creditScore = creditScore; }
}