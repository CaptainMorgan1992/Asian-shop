package com.example.backend.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Entity
@Data
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private float totalPrice;
}
