package com.example.supermarket_service.repository;


import com.example.supermarket_service.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManageRepository extends JpaRepository<Purchase, Long>{
    @Query("SELECT COUNT(DISTINCT p.userId) FROM Purchase p")
    long countDistinctUserIds();
}
