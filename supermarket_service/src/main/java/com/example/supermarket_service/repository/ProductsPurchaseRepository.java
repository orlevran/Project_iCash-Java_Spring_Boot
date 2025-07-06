package com.example.supermarket_service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.supermarket_service.model.DTO.ProductCount;

@Repository
public interface ProductsPurchaseRepository {

    // Retrieves the top three best-selling products across all purchases.
    List<ProductCount> findTopThreeProductsCustom();
}
