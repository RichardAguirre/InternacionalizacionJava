package com.example.parcial2.controllers;

import com.example.parcial2.models.Producto;
import com.example.parcial2.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Flux<Producto> listarProductos() {
        return productoService.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Mono<Producto> agregarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PostMapping("/bulk")
    public Flux<Producto> agregarProductos(@RequestBody List<Producto> productos) {
        return productoService.guardarProductos(productos);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.obtenerProductoPorId(id)
                .flatMap(existingProducto -> {
                    producto.setId(id);
                    return productoService.guardarProducto(producto)
                            .map(ResponseEntity::ok);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Map<String, String>>> eliminarProducto(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .flatMap(producto -> {
                    return productoService.eliminarProducto(id)
                            .then(Mono.just(ResponseEntity.ok(Map.of("message", "Producto con ID " + id + " eliminado correctamente."))));
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Producto con ID " + id + " no encontrado.")));
    }
}