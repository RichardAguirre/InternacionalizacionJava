package com.example.parcial2;

import com.example.parcial2.models.Producto;
import com.example.parcial2.security.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCrearObtenerEliminarProducto() {
        WebTestClient webTestClientWithAuth =
                webTestClient.mutateWith(mockUser("admin").password("123456").roles("ADMIN"));

        Producto productoNuevo = new Producto(null, "ProductoTest", 9999);

        var responsePost = webTestClientWithAuth.post()
                .uri("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(productoNuevo), Producto.class)
                .exchange()
                .expectStatus().isOk();

        Producto productoCreado = responsePost
                .expectBody(Producto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
        assertEquals("ProductoTest", productoCreado.getNombre());

        Long idCreado = productoCreado.getId();

        webTestClientWithAuth.get()
                .uri("/api/productos/{id}", idCreado)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .value(p -> {
                    assertEquals("ProductoTest", p.getNombre());
                    assertEquals(9999, p.getPrecio());
                });

        webTestClientWithAuth.delete()
                .uri("/api/productos/{id}", idCreado)
                .exchange()
                .expectStatus().isOk();

        webTestClientWithAuth.get()
                .uri("/api/productos/{id}", idCreado)
                .exchange()
                .expectStatus().isNotFound();
    }
}