package com.example.supermarket_service.repository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.supermarket_service.model.DTO.ProductCount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

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

    //@Override
    public List<ProductCount> findTopThreeProductsCustom() {

        try
        {
            @SuppressWarnings("unchecked")
            List<ProductCount> results = entityManager.createNativeQuery(TOP_THREE_PRODUCTS_QUERY)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setTupleTransformer((tuple, aliases) ->
                    new ProductCount((String) tuple[0], ((Number) tuple[1]).longValue())
                )
                .getResultList();

            if(results == null || results.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No loyal users found.");
            }

            return results;
        }
        catch (PersistenceException | IllegalArgumentException e)
        {
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);
        }
        
    }
}

/*
 * This code is part of a repository implementation for a supermarket service that retrieves the top three best-selling products from a database.
 * It uses a native SQL query to count the occurrences of each product in the purchases and ranks them to return the top three.
 * The repository handles exceptions and returns a list of ProductCount objects containing the product name and its count.
 * 
 * Since we had to handle a complicated SQL query, we used a native query with a JSON_TABLE function to parse the items_list JSON array.
 * The query has a couple of steps to understand the logic, but not in chronological order:
 * 
 * JSON_TABLE:
 * creates a temporary virtual table. The column 'item_list' in the table 'purchases' is a list of products represented in a TEXT format.
 * The CONCAT function is used to format the string into a JSON array.
 * For example, if the items_list is "apple,banana,orange", it becomes '["apple","banana","orange"]'.
 * Next, the JSON array's elements are extracted into a table format using JSON_TABLE. Every item in each JSON array becomes a row in the virtual table.
 * The new items are inserted into the 'jt' table with a column named 'product'.
 * Example: given an purchases tables with two rows:
 * | id | items_list                |
 * |----|---------------------------|
 * | 1  | apple,banana,orange       |
 * | 2  | banana,orange             |
 * The jt table will look like this:
 * | product   |
 * |-----------|
 * | apple     |
 * | banana    |
 * | orange    |
 * 
 * WITH Clause:
 * The WITH clause defines two Common Table Expressions (CTEs):
 * The first CTE (Common Table Expression) named 'product_count' counts the occurrences of each product across all purchases.
 * It groups the results by product and counts how many times each product appears.
 * 
 * The second CTE named 'ranked_products' ranks the products based on their count using the DENSE_RANK() function.
 * This assigns a rank to each product based on the number of times it appears, with the highest count receiving rank 1.
 * dense_rank() is used to ensure that products with the same count receive the same rank, and the next rank is not skipped.
 * Example: if the counts are:
 * | product   | count |
 * |-----------|-------|
 * | apple     | 5     |
 * | banana    | 5     |
 * | orange    | 3     |
 * The ranks would be:
 * | product   | count | rank |
 * |-----------|-------|------|
 * | apple     | 5     | 1    |
 * | banana    | 5     | 1    |
 * | orange    | 3     | 2    |
 * 
 * Final Selection:
 * The final SELECT statement retrieves the product and its count from the ranked_products CTE where the rank is less than or equal to 3.
 * This means it will return the top three products based on their count.
 */
