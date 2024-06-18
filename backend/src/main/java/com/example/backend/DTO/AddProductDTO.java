package com.example.backend.DTO;

import com.example.backend.Entities.Category;
import lombok.Data;

@Data
public class AddProductDTO {

    private String productName;
    private int category;
    private float price;
    private int stock;
    private int categoryId;
}
