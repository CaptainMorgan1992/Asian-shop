package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;


@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;
    @Column
    @OneToMany
    private ArrayList<Order> orderHistory;

}
