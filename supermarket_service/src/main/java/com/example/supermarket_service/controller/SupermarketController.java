package com.example.supermarket_service.controller;


import com.example.supermarket_service.model.DTO.ProductCount;
import com.example.supermarket_service.model.DTO.UserPurchaseCount;
import com.example.supermarket_service.service.SupermarketService;

import java.util.List;

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

    @GetMapping("/loyal-users")
    public List<UserPurchaseCount> getLoyalUsers() {
        return supermarketService.getLoyalUsers();
    }

    @GetMapping("/top-three-products")
    public List<ProductCount> getTopProducts() {
        return supermarketService.getTopThreeProducts();
    }
    

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}