package com.example.backend.Repository;

import com.example.backend.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Products, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM products WHERE product_name = :productName", nativeQuery = true)
    boolean existsByName(String productName);

}
