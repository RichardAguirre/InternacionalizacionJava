package com.example.parcial2.controllers;

import com.example.parcial2.models.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductoControllerTest {

    @Autowired
    private ProductoController productoController;

    @Test
    public void testListaProductos() {
        Flux<Producto> productos = productoController.listarProductos();

        StepVerifier.create(productos)
                .expectNextMatches(p -> p.getNombre().equals("Tenis") && p.getPrecio() == 120000)
                .expectNextMatches(p -> p.getNombre().equals("Reloj") && p.getPrecio() == 80000)
                .expectNextMatches(p -> p.getNombre().equals("Bufanda") && p.getPrecio() == 30000)
                .verifyComplete();
    }
}