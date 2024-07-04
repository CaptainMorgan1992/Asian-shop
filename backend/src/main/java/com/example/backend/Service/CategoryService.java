package com.example.backend.Service;

import com.example.backend.DTO.AddCategoryDTO;
import com.example.backend.DTO.FetchProductDTO;
import com.example.backend.Entities.Category;
import com.example.backend.Entities.Products;
import com.example.backend.Repository.CategoryRepository;
import com.example.backend.Repository.ProductRepository;
import com.example.backend.interfaces.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;


    @Autowired
    private CategoryService(CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ResponseEntity<String> addNewCategory(AddCategoryDTO addCategoryDTO) {
        boolean isExisting = categoryRepository.findByName(addCategoryDTO.getCategoryName());

        if(isExisting) {
            return new ResponseEntity<>("Category already exists", HttpStatus.BAD_REQUEST);
        } else {
            Category category = new Category();
            category.setCategoryName(addCategoryDTO.getCategoryName());
            categoryRepository.save(category);
            return new ResponseEntity<>("You have successfully created a new category", HttpStatus.OK);
        }
    }


    public List<FetchProductDTO> getProductsByCategory(int categoryId) {
        List<Products> products = categoryRepository.fetchProductsByCategory(categoryId);
        return products.stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }
}
