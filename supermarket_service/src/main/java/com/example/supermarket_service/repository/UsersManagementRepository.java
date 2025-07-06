package com.example.supermarket_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.supermarket_service.model.Purchase;
import com.example.supermarket_service.model.DTO.UserPurchaseCount;

public interface UsersManagementRepository extends JpaRepository<Purchase, Long>{

        /**
     * Counts the number of unique users who made at least one purchase.
     *
     * This query selects the count of distinct user IDs from the Purchase table.
     *
     * JPQL Explanation:
     *   - SELECT COUNT(DISTINCT p.userId) FROM Purchase p
     *   - 'p' is an alias for the Purchase entity
     *   - DISTINCT ensures each user is only counted once
     * 
     * Return: number of distinct users in the purchase history
     */
    @Query("SELECT COUNT(DISTINCT p.userId) FROM Purchase p")
    long countDistinctUserIds();

    /**
     * Retrieves users who have made three or more purchases (considered loyal).
     * 
     * This query groups purchases by user ID and counts how many purchases each user has made.
     * It returns only users whose count is 3 or more, wrapped in a DTO: UserPurchaseCount.
     * 
     * JPQL Explanation:
     *   - SELECT new UserPurchaseCount(p.userId, COUNT(p)) 
     *   - FROM Purchase p GROUP BY p.userId 
     *   - HAVING COUNT(p) >= 3
     *   - Constructs a DTO with the userId and their total number of purchases
     * 
     * Return: list of users with at least 3 purchases, each with their purchase count
     */
   @Query("SELECT new com.example.supermarket_service.model.DTO.UserPurchaseCount(p.userId, COUNT(p)) " +
       "FROM Purchase p GROUP BY p.userId HAVING COUNT(p) >= 3")
    List<UserPurchaseCount> findLoyalUsers();
}