package com.example.backend.interfaces;

import com.example.backend.DTO.FetchProductDTO;
import com.example.backend.Entities.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //FetchProductDTO productToProductDTO(Products product);

    default FetchProductDTO productToProductDTO(Products product) {
        FetchProductDTO dto = new FetchProductDTO();
        dto.setProductId(product.getProductId()); // Explicitly set the productId
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setData(product.getData());
        dto.setSize(product.getSize());
        return dto;
    }
}
