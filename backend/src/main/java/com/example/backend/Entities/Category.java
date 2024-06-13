package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    @Column
    private String categoryName;

}
