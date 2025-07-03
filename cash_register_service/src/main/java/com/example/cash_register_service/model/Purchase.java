package com.example.cash_register_service.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * Entity representing a single purchase record in the 'iCash_Purchases.purchases' table.
 *
 * This class is used to persist user purchases, including:
 * supermarket ID, timestamp, user ID, purchased items, and total amount.
 */
@Entity
@Table(name = "purchases", schema = "iCash_Purchases")
public class Purchase {
    // Auto-generated unique ID for each purchase (primary key).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // The ID of the supermarket where the purchase was made.
    @Column(name = "supermarket_id")
    private String supermarketId;

    // The timestamp when the purchase occurred.
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // The user ID associated with the purchase.
    @Column(name = "user_id")
    private String userId;

    /**
     * The list of items purchased, stored as a comma-separated string.
     * Marked as TEXT for flexibility with longer lists.
     */
    @Column(name = "items_list", columnDefinition = "TEXT")
    private String itemsList;

    // The total monetary amount of the purchase.
    @Column(name = "total_amount")
    private Double totalAmount;

    // Full constructor used when saving a purchase to the database.
    public Purchase(String supermarketId, LocalDateTime timestamp, String userId, String itemsList, Double totalAmount) {
        this.supermarketId = supermarketId;
        this.timestamp = timestamp;
        this.userId = userId;
        this.itemsList = itemsList;
        this.totalAmount = totalAmount;
    }

    // Default constructor required by JPA
    public Purchase() {}

    // ------------- Getters and Setters -------------
    public Long getId() {
        return id;
    }

    public String getSupermarketId() {
        return supermarketId;
    }
    public void setSupermarketId(String supermarketId) {
        this.supermarketId = supermarketId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemsList() {
        return itemsList;
    }
    public void setItemsList(String itemsList) {
        this.itemsList = itemsList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

/**
 * JPA (Java Persistence API) is a Java specification that provides a standard way to map Java objects (entities)
 * to relational database tables and manage data persistence.
 * JPA is not a library itself, but a set of rules/interfaces.
 * The most common implementation of JPA is Hibernate (others include EclipseLink and OpenJPA).
 * 
 * Key Features:
 * Maps Java classes to DB tables using annotations like @Entity, @Table, @Column, etc.
 * Manages CRUD operations through repository interfaces like JpaRepository.
 * Provides query capabilities via JPQL or native SQL.
 * Handles relationships like @OneToMany, @ManyToOne, etc.
 */
