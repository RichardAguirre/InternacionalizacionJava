package com.example.parcial2.services;

import com.example.parcial2.models.Producto;
import com.example.parcial2.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Flux<Producto> obtenerProductos() {
        return Flux.fromIterable(productoRepository.findAll());
    }

    public Mono<Producto> obtenerProductoPorId(Long id) {
        return Mono.justOrEmpty(productoRepository.findById(id));
    }

    public Mono<Producto> guardarProducto(Producto producto) {
        return Mono.just(productoRepository.save(producto));
    }

    public Flux<Producto> guardarProductos(List<Producto> productos) {
        return Flux.fromIterable(productoRepository.saveAll(productos));
    }

    public Mono<Producto> actualizarProducto(Long id, Producto producto) {
        producto.setId(id);
        return Mono.just(productoRepository.save(producto));
    }

    public Mono<Void> eliminarProducto(Long id) {
        productoRepository.deleteById(id);
        return Mono.empty();
    }
}