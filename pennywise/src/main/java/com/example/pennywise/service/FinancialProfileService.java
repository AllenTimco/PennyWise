package com.example.pennywise.service;

import com.example.pennywise.dto.FinancialProfileRequest;
import com.example.pennywise.dto.FinancialProfileResponse;
import com.example.pennywise.entity.FinancialProfile;
import com.example.pennywise.repository.FinancialProfileRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinancialProfileService {

    private static final long DEVELOPMENT_PROFILE_ID = 1L;
    private final FinancialProfileRepository profileRepository;

    public FinancialProfileService(FinancialProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public FinancialProfileResponse get() {
        return profileRepository.findById(DEVELOPMENT_PROFILE_ID)
                .map(FinancialProfileResponse::from)
                .orElseThrow(() -> new IllegalArgumentException("Financial profile has not been created"));
    }

    public FinancialProfileResponse save(FinancialProfileRequest request) {
        validate(request);
        FinancialProfile profile = profileRepository.findById(DEVELOPMENT_PROFILE_ID)
                .orElseGet(() -> new FinancialProfile(request.monthlyIncome(), request.monthlyExpenses(),
                        request.totalSavings(), request.totalDebt(), request.creditScore()));
        profile.setMonthlyIncome(request.monthlyIncome());
        profile.setMonthlyExpenses(request.monthlyExpenses());
        profile.setTotalSavings(request.totalSavings());
        profile.setTotalDebt(request.totalDebt());
        profile.setCreditScore(request.creditScore());
        profileRepository.save(profile);
        return FinancialProfileResponse.from(profile);
    }

    private void validate(FinancialProfileRequest request) {
        if (request == null || request.monthlyIncome() == null || request.monthlyExpenses() == null
                || request.totalSavings() == null || request.totalDebt() == null) {
            throw new IllegalArgumentException("All financial amounts are required");
        }
        if (request.monthlyIncome().compareTo(BigDecimal.ZERO) < 0
                || request.monthlyExpenses().compareTo(BigDecimal.ZERO) < 0
                || request.totalSavings().compareTo(BigDecimal.ZERO) < 0
                || request.totalDebt().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Financial amounts cannot be negative");
        }
    }
}