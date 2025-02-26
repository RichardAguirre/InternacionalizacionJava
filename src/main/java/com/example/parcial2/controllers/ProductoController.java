package com.example.parcial2.controllers;

import com.example.parcial2.models.Producto;
import com.example.parcial2.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping
    public Mono<Producto> agregarProducto(@RequestBody Producto producto) {
        return Mono.just(productoRepository.save(producto));
    }

    @PostMapping("/bulk")
    public Flux<Producto> agregarProductos(@RequestBody List<Producto> productos) {
        return Flux.fromIterable(productoRepository.saveAll(productos));
    }

    @GetMapping
    public Flux<Producto> listarProductos() {
        return Flux.fromIterable(productoRepository.findAll());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.map(producto -> Mono.just(ResponseEntity.ok(producto)))
                .orElseGet(() -> Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto productoActualizado = productoRepository.save(producto);
        return Mono.just(ResponseEntity.ok(productoActualizado));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if (productoOptional.isPresent()) {
            productoRepository.deleteById(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto con ID " + id + " eliminado correctamente.");

            return Mono.just(ResponseEntity.ok(response));
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Producto con ID " + id + " no encontrado.");
            return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(response));
        }
    }
}