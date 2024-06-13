package com.example.backend.Service;


import com.example.backend.DTO.RegisterUserDTO;
import com.example.backend.Entities.Role;
import com.example.backend.Entities.User;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<String> registerNewUser(RegisterUserDTO registerUserDTO) {

        if(userRepository.existsByUsername(registerUserDTO.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerUserDTO.getEmail())) {
            return new ResponseEntity<>("User email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();

        user.setUsername(registerUserDTO.getUsername());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(registerUserDTO.getPassword());
        user.setStreet(registerUserDTO.getStreet());
        user.setStreetNo(registerUserDTO.getStreetNo());
        user.setZipCode(registerUserDTO.getZipCode());
        user.setCity(registerUserDTO.getCity());
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<String>("Registration successful", HttpStatus.OK);

    }
}
