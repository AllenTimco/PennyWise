package com.example.pennywise.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculators")
@CrossOrigin(origins = "*")
public class CalculatorController {

    private static final String DISCLAIMER = "Estimate only. Not financial advice.";

    @PostMapping("/compound-interest")
    public CalculationResponse compoundInterest(@RequestBody CompoundInterestRequest request) {
        double monthlyRate = request.annualRate() / 100 / 12;
        int months = request.years() * 12;
        double value = futureValue(request.startingAmount(), request.monthlyContribution(), monthlyRate, months);
        return new CalculationResponse(value, DISCLAIMER);
    }

    @PostMapping("/loan")
    public CalculationResponse loan(@RequestBody LoanRequest request) {
        double monthlyRate = request.annualRate() / 100 / 12;
        int months = request.years() * 12;
        double payment = monthlyRate == 0 || months == 0
                ? (months == 0 ? 0 : request.loanAmount() / months)
                : request.loanAmount() * monthlyRate * Math.pow(1 + monthlyRate, months)
                        / (Math.pow(1 + monthlyRate, months) - 1);
        return new CalculationResponse(payment, DISCLAIMER);
    }

    @PostMapping("/tax")
    public CalculationResponse tax(@RequestBody TaxRequest request) {
        return new CalculationResponse(request.annualIncome() * request.estimatedTaxRate() / 100, DISCLAIMER);
    }

    @PostMapping("/investment")
    public CalculationResponse investment(@RequestBody InvestmentRequest request) {
        double monthlyRate = request.annualReturn() / 100 / 12;
        int months = request.years() * 12;
        return new CalculationResponse(
                futureValue(request.startingAmount(), request.monthlyContribution(), monthlyRate, months), DISCLAIMER);
    }

    @PostMapping("/budget")
    public CalculationResponse budget(@RequestBody BudgetRequest request) {
        double expenses = request.housing() + request.food() + request.transportation() + request.otherExpenses();
        return new CalculationResponse(request.monthlyIncome() - expenses, DISCLAIMER);
    }

    private double futureValue(double startingAmount, double monthlyContribution, double monthlyRate, int months) {
        if (monthlyRate == 0) {
            return startingAmount + monthlyContribution * months;
        }
        return startingAmount * Math.pow(1 + monthlyRate, months)
                + monthlyContribution * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate);
    }

    public record CalculationResponse(double result, String disclaimer) { }
    public record CompoundInterestRequest(double startingAmount, double annualRate, int years, double monthlyContribution) { }
    public record LoanRequest(double loanAmount, double annualRate, int years) { }
    public record TaxRequest(double annualIncome, double estimatedTaxRate) { }
    public record InvestmentRequest(double startingAmount, double monthlyContribution, double annualReturn, int years) { }
    public record BudgetRequest(double monthlyIncome, double housing, double food, double transportation, double otherExpenses) { }
}