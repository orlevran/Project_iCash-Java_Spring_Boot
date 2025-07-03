package com.example.supermarket_service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SupermarketController {
    @GetMapping("/supermarket/method")
    public String getMethodName() {
        return "Supermarket Service";
    }
}