package com.example.backend.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDTO {

    @Size(min =5, message = "Username must be at least 5 characters long")
    private String username;

    @Email(message= "Invalid email")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern.List({
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).+$", message = "Password must contain one digit and one uppercase letter")})
    private String password;

    private String street;

    private int streetNo;

    private int zipCode;

    private String city;
}
