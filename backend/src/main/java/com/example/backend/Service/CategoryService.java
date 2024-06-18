package com.example.backend.Service;

import com.example.backend.DTO.AddCategoryDTO;
import com.example.backend.Entities.Category;
import com.example.backend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
}
