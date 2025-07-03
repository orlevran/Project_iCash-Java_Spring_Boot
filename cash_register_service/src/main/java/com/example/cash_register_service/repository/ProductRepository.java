package com.example.cash_register_service.repository;

import com.example.cash_register_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

/**
 * Repository interface for accessing product data from the database.
 *
 * This interface is essential for Spring Data JPA to generate the underlying
 * implementation automatically at runtime. It allows services (ProductCacheService in this case)
 * to interact with the database without manually writing SQL or DAO logic.
 *
 * If this interface is missing or not properly defined, any service that depends on it
 * will fail to start — resulting in a runtime error indicating that the repository is not available.
 *
 * To ensure Spring can detect and auto-implement this repository,
 * it must be in a package scanned by @SpringBootApplication.
 */