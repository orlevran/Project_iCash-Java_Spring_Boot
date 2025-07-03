package com.example.supermarket_service.controller;


import com.example.supermarket_service.service.SupermarketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supermarket")
public class SupermarketController {

    private final SupermarketService supermarketService;

    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping("/total-users")
    public long getTotalUsers() {
        return supermarketService.getTotalUniqueUsers();
    }
}