package com.example.pennywise.controller;

import com.example.pennywise.dto.FinancialProfileRequest;
import com.example.pennywise.dto.FinancialProfileResponse;
import com.example.pennywise.service.FinancialProfileService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financial-profile")
@CrossOrigin(origins = "*")
public class FinancialProfileController {

    private final FinancialProfileService profileService;

    public FinancialProfileController(FinancialProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public FinancialProfileResponse get() { return profileService.get(); }

    @PutMapping
    public FinancialProfileResponse save(@RequestBody FinancialProfileRequest request) {
        return profileService.save(request);
    }
}