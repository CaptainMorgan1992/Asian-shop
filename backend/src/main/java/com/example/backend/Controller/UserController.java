package com.example.backend.Controller;


import com.example.backend.DTO.FetchUserDTO;
import com.example.backend.DTO.LoginDTO;
import com.example.backend.DTO.RegisterUserDTO;
import com.example.backend.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewUser(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
    return userService.registerNewUser(registerUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FetchUserDTO> getUserById(@PathVariable int id){
        return userService.findUserByUserId(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
        return userService.authenticateUser(loginDTO);
    }

}
