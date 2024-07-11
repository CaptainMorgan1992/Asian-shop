package com.example.backend.Controller;

import com.example.backend.Entities.Cart;
import com.example.backend.Service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable Integer productId, Principal principal) {
        return cartService.addToCart(productId, principal);
    }

}
