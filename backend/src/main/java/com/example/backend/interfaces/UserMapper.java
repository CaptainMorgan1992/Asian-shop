package com.example.backend.interfaces;

import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    FetchUserDTO userToUserDTO(User user);

}
