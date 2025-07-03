package com.example.cash_register_service.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.cash_register_service.model.Product;
import com.example.cash_register_service.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

/**
 * Service responsible for caching product data in memory.
 *
 * This cache is built at application startup using PostConstruct interface, pulling product data from
 * the database via ProductRepository. The cache improves performance by avoiding repeated DB lookups
 * for product prices during purchases.
 *
 * The cache structure is a simple Map<String, Double> where:
 * Key = product name
 * Value = product price
 */

@Service
public class ProductCacheService {
    private final ProductRepository productRepository;

    private final Map<String, Double> productCache = new HashMap<>();

    public ProductCacheService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<String, Double> getProductCache() {
        return productCache;
    }

    @PostConstruct
    public void loadCache() {
        productCache.clear(); // Ensure no leftovers on reload

        // Load products from the repository and populate the cache
        // Using forEach to avoid duplicates in the cache
        productRepository.findAll().forEach(product -> {
            String name = product.getName();

            // Only add if not already in the map
            if (!productCache.containsKey(name)) {
                productCache.put(name, product.getPrice());
            }
        });

        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            productCache.put(product.getName(), product.getPrice());
        }
    }
}
