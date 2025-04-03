package com.example.parcial2.controllers;

import com.example.parcial2.models.Producto;
import com.example.parcial2.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductoControllerTest {

    @Autowired
    private ProductoController productoController;
    
    @Autowired
    private ProductoService productoService;

    @Test
    public void testListaProductos() {
        Producto producto1 = new Producto(null, "Tenis", 120000);
        Producto producto2 = new Producto(null, "Reloj", 80000);
        Producto producto3 = new Producto(null, "Bufanda", 30000);

        productoService.guardarProducto(producto1).block();
        productoService.guardarProducto(producto2).block();
        productoService.guardarProducto(producto3).block();
        
        Flux<Producto> productos = productoController.listarProductos();
        StepVerifier.create(productos)
                .recordWith(java.util.ArrayList::new)
                .thenConsumeWhile(p -> true)
                .consumeRecordedWith(lista -> {
                    boolean tenisExiste = lista.stream()
                            .anyMatch(p -> "Tenis".equals(p.getNombre())
                                    && p.getPrecio() == 120000);
                    boolean relojExiste = lista.stream()
                            .anyMatch(p -> "Reloj".equals(p.getNombre())
                                    && p.getPrecio() == 80000);
                    boolean bufandaExiste = lista.stream()
                            .anyMatch(p -> "Bufanda".equals(p.getNombre())
                                    && p.getPrecio() == 30000);

                    assertTrue(tenisExiste, "No se encontró el producto 'Tenis'");
                    assertTrue(relojExiste, "No se encontró el producto 'Reloj'");
                    assertTrue(bufandaExiste, "No se encontró el producto 'Bufanda'");
                })
                .verifyComplete();
    }
}