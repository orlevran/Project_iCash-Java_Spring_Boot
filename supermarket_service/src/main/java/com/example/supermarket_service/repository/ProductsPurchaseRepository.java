package com.example.supermarket_service.repository;

import java.util.List;

import com.example.supermarket_service.model.DTO.ProductCount;

public interface ProductsPurchaseRepository {
    List<ProductCount> findTopThreeProductsCustom();
}
