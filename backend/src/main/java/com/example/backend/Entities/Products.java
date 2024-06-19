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
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private int stock;

    @Lob
    @Column
    private byte [] data;

    @Column
    private long size;
}
