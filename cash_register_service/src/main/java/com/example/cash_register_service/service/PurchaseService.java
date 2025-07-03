package com.example.cash_register_service.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.cash_register_service.model.Purchase;
import com.example.cash_register_service.model.DTO.PurchaseRequest;
import com.example.cash_register_service.repository.PurchaseRepository;

/**
 * Service layer responsible for processing purchase requests.
 * Validates input, calculates totals, manages user identification, and persists purchases.
 */

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductCacheService productCacheService;
    private final String[] validSupermarketIds = {"SMKT001", "SMKT002", "SMKT003"};

    // Constructor-based injection is used here, which is preferred for required dependencies.
    public PurchaseService(PurchaseRepository purchaseRepository, ProductCacheService productCacheService) {
        this.purchaseRepository = purchaseRepository;
        this.productCacheService = productCacheService;
    }

    /**
     * Handles creation of a purchase.
     * Performs multiple validations, resolves user identity, calculates total cost, and persists the purchase.
     *
     * param: request the incoming purchase request
     * return: the persisted Purchase object
     * throws: IllegalArgumentException if validation fails
     */
    public Purchase makePurchase(PurchaseRequest request){

        // Validate request body and contents
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Purchase request cannot be null or empty");
        }
        if (request.getSupermarketId() == null || request.getSupermarketId().isEmpty()) {
            throw new IllegalArgumentException("Supermarket ID cannot be null or empty");
        }
        if(!Arrays.asList(validSupermarketIds).contains(request.getSupermarketId().toUpperCase())) {
            throw new IllegalArgumentException("Invalid supermarket ID");
        }
        if (request.getItems().size() > productCacheService.getProductCache().size()) {
            throw new IllegalArgumentException(String.format("Cannot purchase more than %d items at once", productCacheService.getProductCache().size()));
        }

        // 1. Get or generate user ID
        String userId = request.getUserId();
        if (userId == null || userId.isEmpty() || !purchaseRepository.existsByUserId(userId)) {
            userId = UUID.randomUUID().toString();
        }

        // 2. Calculate total amount
        Map<String, Double> productCache = productCacheService.getProductCache();
        double totalAmount = request.getItems().stream().map(
            item -> {
                Double price = productCache.get(item);
                if (price == null) {
                throw new IllegalArgumentException("Item not found: " + item); // Ensure item exists in cache
        }
            return price;
        }).mapToDouble(Double::doubleValue).sum();

        // 3. Convert items list to a string
        String itemsListAString = String.join(",", request.getItems());

        // 4. Create Purchase object
        Purchase purchase = new Purchase(request.getSupermarketId().toUpperCase(), LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC), userId, itemsListAString, totalAmount);

        // 5. Save purchase to the database
        return purchaseRepository.save(purchase);
    }
}
