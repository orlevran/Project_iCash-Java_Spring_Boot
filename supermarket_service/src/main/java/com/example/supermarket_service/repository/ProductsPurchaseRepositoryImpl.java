package com.example.supermarket_service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.supermarket_service.model.DTO.ProductCount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ProductsPurchaseRepositoryImpl implements ProductsPurchaseRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String TOP_THREE_PRODUCTS_QUERY = """
    WITH product_count AS (
        SELECT product, COUNT(*) AS count
        FROM purchases,
        JSON_TABLE(
            CONCAT('["', REPLACE(items_list, ',', '","'), '"]'),
            "$[*]" COLUMNS(product VARCHAR(40) PATH "$")
        ) AS jt
        GROUP BY product
    ),
    ranked_products AS (
        SELECT product, count, DENSE_RANK() OVER (ORDER BY count DESC) AS products_rank
        FROM product_count
    )
    SELECT product, count
    FROM ranked_products
    WHERE products_rank <= 3
    ORDER BY count DESC
    """;

    /*
    final String TOP_THREE_PRODUCTS_QUERY = 
        "WITH product_count AS (" +
            "SELECT product, COUNT(*) AS count " +
            "FROM purchases, " +
            "JSON_TABLE(" +
                "CONCAT('[\"', REPLACE(items_list, ',', '\",\"'), '\"]'), " +
                "\"$[*]\" COLUMNS(product VARCHAR(40) PATH \"$\")" +
            ") AS jt " +
            "GROUP BY product" +
            "), " +
        "ranked_products AS (" +
            "SELECT product, count, DENSE_RANK() OVER (ORDER BY count DESC) AS products_rank " +
            "FROM product_count" +
            ") " +
            "SELECT product, count " +
            "FROM ranked_products " +
            "WHERE products_rank <= 3 " +
            "ORDER BY count DESC";
            */

    @Override
    public List<ProductCount> findTopThreeProductsCustom() {
        /*
        Query query = entityManager.createNativeQuery(TOP_THREE_PRODUCTS_QUERY, ProductCount.class);
        return query.getResultList();
        */
        @SuppressWarnings("unchecked")
        List<ProductCount> results = entityManager.createNativeQuery(TOP_THREE_PRODUCTS_QUERY)
            .unwrap(org.hibernate.query.NativeQuery.class)
            .setTupleTransformer((tuple, aliases) ->
                new ProductCount((String) tuple[0], ((Number) tuple[1]).longValue())
            )
            .getResultList();
        return results;
    }
}
