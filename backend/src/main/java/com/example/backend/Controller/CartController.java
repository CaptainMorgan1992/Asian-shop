package com.example.backend.Controller;

import com.example.backend.Entities.Cart;
import com.example.backend.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable Integer productId, Principal principal) {
        System.out.println("Hej!");
        if(principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not authenticated");
        }
        System.out.println("User: " + principal.getName());
        return cartService.addToCart(productId, principal);
    }

}
