package com.example.backend.DTO;

import lombok.Data;

@Data
public class FetchUserDTO {
    private String username;
    private String email;
    private String street;
    private  int streetNo;
    private int zipCode;
    private String city;
}
