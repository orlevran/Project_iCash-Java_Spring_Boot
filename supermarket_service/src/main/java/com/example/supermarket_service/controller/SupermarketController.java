package com.example.supermarket_service.controller;


import com.example.supermarket_service.model.DTO.ProductCount;
import com.example.supermarket_service.model.DTO.UserPurchaseCount;
import com.example.supermarket_service.service.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller that exposes API endpoints for analytics and statistics related to users and products.
 * 
 * Base URL: /supermarket
 * 
 * This controller acts as the entry point for the second microservice, exposing operations for:
 * - Counting total unique users
 * - Identifying loyal users (3+ purchases)
 * - Retrieving top-selling products
 */

@RestController
@RequestMapping("/supermarket")
public class SupermarketController {

    private final UsersManagementService usersManagementService;
    private final ProductsManagementService productsManagementService;

    public SupermarketController(UsersManagementService usersManagementService, ProductsManagementService productsManagementService) {
        this.usersManagementService = usersManagementService;
        this.productsManagementService = productsManagementService;
    }

    @GetMapping("/total-users")
    public long getTotalUsers() {
        return usersManagementService.getTotalUniqueUsers();
    }

    @GetMapping("/loyal-users")
    public List<UserPurchaseCount> getLoyalUsers() {
        return usersManagementService.getLoyalUsers();
    }

    @GetMapping("/top-three-products")
    public List<ProductCount> getTopProducts() {
        return productsManagementService.getTopThreeProducts();
    }
    

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}