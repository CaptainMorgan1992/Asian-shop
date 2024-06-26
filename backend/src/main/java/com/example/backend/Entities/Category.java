package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column (nullable = false)
    private String categoryName;

}
