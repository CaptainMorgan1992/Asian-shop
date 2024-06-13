package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column
    private String productName;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int stock;
}
