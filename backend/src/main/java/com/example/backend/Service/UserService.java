package com.example.backend.Service;

import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.DTO.LoginDTO;
import com.example.backend.DTO.RegisterUserDTO;
import com.example.backend.Entities.Role;
import com.example.backend.Entities.User;
import com.example.backend.Repository.RoleRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.interfaces.UserMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    //userMapper behöver vara kvar som den är nu, annars blir det 403 forbidden vid inlogg
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }


        public ResponseEntity<String> registerNewUser(RegisterUserDTO registerUserDTO){

            if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }
            if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
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
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);

        }

        public ResponseEntity<FetchUserDTO> findUserByUserId (int id){
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                FetchUserDTO fetchUserDTO = UserMapper.INSTANCE.userToUserDTO(userOptional.get());
                return new ResponseEntity<>(fetchUserDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


        public ResponseEntity<String> authenticateUser (LoginDTO loginDTO) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                return new ResponseEntity<>(" is successfully logged in.", HttpStatus.OK);

            } catch (AuthenticationException ex) {
                return new ResponseEntity<>("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
            }
        }
    }

