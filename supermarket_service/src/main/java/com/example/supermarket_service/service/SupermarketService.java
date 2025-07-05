package com.example.supermarket_service.service;


import com.example.supermarket_service.model.DTO.*;
import com.example.supermarket_service.repository.ManageRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SupermarketService {

    private final ManageRepository manageRepository;

    public SupermarketService(ManageRepository manageRepository) {
        this.manageRepository = manageRepository;
    }

    public long getTotalUniqueUsers() {
        return manageRepository.countDistinctUserIds();
    }

    public List<UserPurchaseCount> getLoyalUsers() {
        return manageRepository.findLoyalUsers();
    }

    public List<ProductCount> getTopThreeProducts() {
        return manageRepository.findTopThreeProductsCustom();
    }
}
