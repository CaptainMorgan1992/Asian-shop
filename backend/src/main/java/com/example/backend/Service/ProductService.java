package com.example.backend.Service;

import com.example.backend.DTO.AddProductDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    private ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ResponseEntity<String> addNewProduct(AddProductDTO addProductDTO, MultipartFile file) throws IOException {

        boolean doesExistInDB = productRepository.existsByName(addProductDTO.getProductName());
        if (doesExistInDB) {
            return new ResponseEntity<>("The Product already exists in DB", HttpStatus.BAD_REQUEST);
        } else {
            Optional<Category> categoryOptional = categoryRepository.findById(String.valueOf(addProductDTO.getCategoryId()));
            if (!categoryOptional.isPresent()) {
                return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
            }

            boolean isImageSizeValid = isValidImageSize(file);
            if (isImageSizeValid) {
                Products product = new Products();

                Category category = categoryOptional.get();
                product.setProductName(addProductDTO.getProductName());
                product.setCategory(category);
                product.setPrice(addProductDTO.getPrice());
                product.setStock(addProductDTO.getStock());
                product.setData(file.getBytes());
                product.setSize(file.getSize());
                productRepository.save(product);
                return new ResponseEntity<>("You have successfully added a new product to the DB", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Image size is too large", HttpStatus.BAD_REQUEST);
            }
        }

    }

    public boolean isValidImageSize(MultipartFile file) {
        long imageSize = file.getSize();
        long maxSize = 2 * 1024 * 1024; // 2mb

        return imageSize <= maxSize;
    }

    public List<FetchProductDTO> fetchAllProducts() {
        List<Products> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

}
