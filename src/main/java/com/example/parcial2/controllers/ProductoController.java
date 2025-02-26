package com.example.parcial2.controllers;

import com.example.parcial2.models.Producto;
import com.example.parcial2.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}