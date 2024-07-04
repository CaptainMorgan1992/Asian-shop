package com.example.backend.Controller;


import com.example.backend.DTO.AddCategoryDTO;
import com.example.backend.DTO.FetchProductDTO;
import com.example.backend.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    private CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewCategory(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
        return categoryService.addNewCategory(addCategoryDTO);
    }

    @GetMapping("/fetchProductsFromCategory/{id}")
    public ResponseEntity<List<FetchProductDTO>> fetchProductsFromCategory(@PathVariable int id) {
        List<FetchProductDTO> products = categoryService.getProductsByCategory(id);
        return ResponseEntity.ok(products);
    }
}
