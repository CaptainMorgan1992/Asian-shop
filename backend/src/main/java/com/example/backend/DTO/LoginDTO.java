package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class LoginDTO {

    private String username;
    private String password;
}
