package com.example.backend.Service;

import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.DTO.RegisterUserDTO;
import com.example.backend.Entities.Role;
import com.example.backend.Entities.User;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.interfaces.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setStreet(registerUserDTO.getStreet());
        user.setStreetNo(registerUserDTO.getStreetNo());
        user.setZipCode(registerUserDTO.getZipCode());
        user.setCity(registerUserDTO.getCity());
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<String>("Registration successful", HttpStatus.OK);

    }

    /*
    public FetchUserDTO fetchUserDetails(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

     */

    public ResponseEntity<FetchUserDTO> findUserByUserId(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            FetchUserDTO fetchUserDTO = UserMapper.INSTANCE.userToUserDTO(userOptional.get());
            return new ResponseEntity<>(fetchUserDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*
    private FetchUserDTO convertToDTO(User user) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            return userDTO;

    }

     */
}
