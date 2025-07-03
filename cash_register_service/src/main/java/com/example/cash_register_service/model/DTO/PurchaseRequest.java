package com.example.cash_register_service.model.DTO;

import java.util.List;

/**
 * A DTO (Data Transfer Object) used for handling incoming purchase requests via the REST API.
 *
 * This object is received as JSON in a POST request body and mapped automatically by Spring.
 */

public class PurchaseRequest {
    private String supermarketId; // One out of 3 options: SMKT001, SMKT002, SMKT003
    private List<String> items; // list of product names
    private String userId; // optional. Can be null

    //--------- Getters and Setters ---------
    public String getSupermarketId() {
        return supermarketId;
    }
    public void setSupermarketId(String supermarketId) {
        this.supermarketId = supermarketId;
    }

    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
