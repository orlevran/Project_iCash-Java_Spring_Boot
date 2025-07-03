package com.example.cash_register_service.model;

import jakarta.persistence.*;

/**
 * Entity representing a product in the 'products_list' table.
 *
 * This class is managed by JPA (Java Persistence API) and reflects each row in the database table.
 * It's used primarily to populate the product cache and provide pricing info for purchases.
 */

@Entity
@Table(name = "products_list")
public class Product {
    /**
     * Primary key: product name.
     * Assumes that each product name is unique in the database.
     */
    @Id
    @Column(name = "product_name")
    private String name;

    // Price of the product, mapped from the 'unit_price' column.
    @Column(name = "unit_price")
    private Double price;

    // Constructors:
    // 1. Default constructor required by JPA.
    public Product() {}
    // 2. Full constructor for manual creation or testing.
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getters (no setters provided as the product details are not expected to change):
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
