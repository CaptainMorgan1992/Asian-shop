package com.example.backend.DTO;

import lombok.Data;

@Data
public class FetchProductDTO {
    private String productName;
    private float price;
    private int stock;
    private byte [] data;
    private long size;

}
