package com.example.backend.Service;

import com.example.backend.Entities.Cart;
import com.example.backend.Entities.Products;
import com.example.backend.Entities.User;
import com.example.backend.Repository.CartRepository;
import com.example.backend.Repository.ProductRepository;
import com.example.backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(Integer productId, Principal principal) {
        Products product = productRepository.findById(productId).get();
        String username = principal.getName();
        System.out.println(username);
        User user = null;
        if(username != null) {
            user = userRepository.findByUsername(username);
        }
        if (product.getStock() > 0) {
            Cart cart = new Cart(product, user);
            return cartRepository.save(cart);
        }
        return null;
    }
}
