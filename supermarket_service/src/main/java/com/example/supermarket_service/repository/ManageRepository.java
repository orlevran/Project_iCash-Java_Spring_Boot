package com.example.supermarket_service.repository;


import com.example.supermarket_service.model.Purchase;
import com.example.supermarket_service.model.DTO.ProductCount;
import com.example.supermarket_service.model.DTO.UserPurchaseCount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManageRepository extends JpaRepository<Purchase, Long>, ProductsPurchaseRepository{

    @Query("SELECT COUNT(DISTINCT p.userId) FROM Purchase p")
    long countDistinctUserIds();

    //@Query("SELECT user_id, COUNT(user_id) FROM purchases GROUP BY user_id HAVING COUNT(user_id) >= 3")
   @Query("SELECT new com.example.supermarket_service.model.DTO.UserPurchaseCount(p.userId, COUNT(p)) " +
       "FROM Purchase p GROUP BY p.userId HAVING COUNT(p) >= 3")
    List<UserPurchaseCount> findLoyalUsers();
}
