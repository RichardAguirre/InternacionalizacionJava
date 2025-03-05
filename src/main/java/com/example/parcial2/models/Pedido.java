package com.example.parcial2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double total;

    public Pedido(String producto, int cantidad, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }
}