package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne
    private Products product;

    @OneToOne
    private User user;

    public Cart(Products product, User user) {
    }

    public Cart() {

    }
}
