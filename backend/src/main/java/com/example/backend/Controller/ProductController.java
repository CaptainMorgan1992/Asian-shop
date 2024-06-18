package com.example.backend.Controller;


import com.example.backend.DTO.AddProductDTO;
import com.example.backend.Service.ProductService;
import com.example.backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@Valid @RequestBody AddProductDTO addProductDTO) {
        return productService.addNewProduct(addProductDTO);
    }
}
