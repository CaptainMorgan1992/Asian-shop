package com.example.backend.Controller;


import com.example.backend.DTO.AddProductDTO;
import com.example.backend.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProduct(
            @Valid
            @RequestPart("data") AddProductDTO addProductDTO,
            @RequestPart("file") MultipartFile file) throws IOException {
        return productService.addNewProduct(addProductDTO, file);
    }
}
