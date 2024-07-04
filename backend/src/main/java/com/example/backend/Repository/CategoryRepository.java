package com.example.backend.Repository;

import com.example.backend.Entities.Category;
import com.example.backend.Entities.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM categories WHERE category_name = :categoryName", nativeQuery = true)
    boolean findByName(String categoryName);

    @Transactional
    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
    List<Products> fetchProductsByCategory(@Param("categoryId") int categoryId);
}
