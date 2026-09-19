package com.example.pennywise;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class BankController {

    @GetMapping("/status")
    public Map<String, String> getBackendStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "connected");
        response.put("message", "Your VS Code Spring Boot backend is successfully talking to your HTML frontend!");
        return response;
    }
}