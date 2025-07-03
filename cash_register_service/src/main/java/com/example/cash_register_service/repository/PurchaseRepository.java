package com.example.cash_register_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cash_register_service.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsByUserId(String userId);
}

/**
 * Repository interface for accessing and managing purchase records in the database.
 *
 * This interface extends Spring Data JPA's JpaRepository, which automatically provides
 * basic CRUD (Create, Read, Update, Delete) operations for the Purchase entity.
 *
 * The custom method "existsByUserId(String userId)" is a query method derived from the
 * method name. Spring will automatically generate a query for it, which checks if any purchases
 * exist in the database for a specific user ID.
 *
 * If this interface is missing, not defined correctly, or placed outside of a package scanned by
 * the application context, Spring will fail to autowire the dependent services like
 * (PurchaseService in this case). This will cause the application context to fail to start,
 * resulting in a runtime error indicating that the repository is not available.
 */
