package com.example.supermarket_service.model.DTO;

/**
 * Data Transfer Object representing the count of purchases for a specific product.
 *
 * productId: Identifier or name of the product
 * purchaseCount: Number of times the product was purchased
 */

public class ProductCount {
    private String productId;
    private long purchaseCount;

    public ProductCount() {
        // No-arg constructor required by some JPA providers
    }

    public ProductCount(String productId, long purchaseCount) {
        this.productId = productId;
        this.purchaseCount = purchaseCount;
    }

    public String getProductId() {
        return productId;
    }

    public long getPurchaseCount() {
        return purchaseCount;
    }
}
