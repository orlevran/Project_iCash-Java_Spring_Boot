package com.example.supermarket_service.model.DTO;

public class UserPurchaseCount {

    private String userId;
    private long purchaseCount;

    public UserPurchaseCount() {
        // No-arg constructor required by some JPA providers
    }

    public UserPurchaseCount(String userId, long purchaseCount) {
        this.userId = userId;
        this.purchaseCount = purchaseCount;
    }

    public String getUserId() {
        return userId;
    }

    public long getPurchaseCount() {
        return purchaseCount;
    }
}