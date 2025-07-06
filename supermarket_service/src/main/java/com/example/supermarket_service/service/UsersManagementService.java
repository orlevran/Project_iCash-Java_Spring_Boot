package com.example.supermarket_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.supermarket_service.model.DTO.UserPurchaseCount;
import com.example.supermarket_service.repository.UsersManagementRepository;


/**
 * Service layer responsible for business logic related to user purchase analytics.
 *
 * This service acts as a mediator between the controller layer and the data access layer,
 * encapsulating operations that involve analyzing user behavior based on purchase history.
 *
 * It provides operations for:
 * - Counting total unique users who have made purchases
 * - Identifying loyal users who made 3 or more purchases
 */
@Service
public class UsersManagementService {
    private final UsersManagementRepository usersManagementRepository;

    public UsersManagementService(UsersManagementRepository usersManagementRepository) {
        this.usersManagementRepository = usersManagementRepository;
    }

    /**
     * Returns the number of distinct users who have made at least one purchase.
     *
     * Delegates to a custom JPQL query in the repository, which performs:
     * SELECT COUNT(DISTINCT p.userId) FROM Purchase p
     *
     * Returns the count of unique users
     */
    public long getTotalUniqueUsers() {
        return usersManagementRepository.countDistinctUserIds();
    }

    /**
     * Retrieves a list of "loyal" users — defined as users who made 3 or more purchases.
     *
     * Delegates to a repository query that:
     * - Groups by userId
     * - Filters with HAVING COUNT(p) >= 3
     * - Returns DTOs (UserPurchaseCount) with the userId and number of purchases
     *
     * Returns a list of UserPurchaseCount DTOs for loyal users
     */
    public List<UserPurchaseCount> getLoyalUsers() {
        return usersManagementRepository.findLoyalUsers();
    }
}
