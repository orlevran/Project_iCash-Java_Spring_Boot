package com.example.supermarket_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.supermarket_service.model.DTO.ProductCount;
import com.example.supermarket_service.repository.ProductsPurchaseRepository;

/**
 * Service layer responsible for handling business logic related to product analytics.
 *
 * This service acts as a bridge between the controller layer and the repository layer,
 * encapsulating the logic required to retrieve statistical data about product purchases.
 *
 * Currently, it provides a single operation: fetching the top 3 most purchased products.
 */
@Service
public class ProductsManagementService {
    private final ProductsPurchaseRepository productsPurchaseRepository;

    public ProductsManagementService(ProductsPurchaseRepository productsPurchaseRepository) {
        this.productsPurchaseRepository = productsPurchaseRepository;
    }

    public List<ProductCount> getTopThreeProducts() {
        return productsPurchaseRepository.findTopThreeProductsCustom();
    }
}
