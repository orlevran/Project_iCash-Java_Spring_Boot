package com.example.supermarket_service.model.DTO;

    /**
     * Data Transfer Object representing the count of purchases for a specific user.
     * userId: The ID of the user
     * purchaseCount: The total number of purchases by the user
     */

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