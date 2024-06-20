package com.example.backend.interfaces;

import com.example.backend.DTO.FetchProductDTO;
import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.Entities.Products;
import com.example.backend.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    FetchProductDTO productToProductDTO(Products product);
}
