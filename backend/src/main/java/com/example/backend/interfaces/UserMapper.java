package com.example.backend.interfaces;

import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    FetchUserDTO userToUserDTO(User user);

}
