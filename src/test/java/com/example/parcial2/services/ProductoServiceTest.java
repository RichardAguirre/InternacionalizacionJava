package com.example.parcial2.services;

import com.example.parcial2.models.Producto;
import com.example.parcial2.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
        producto1 = new Producto(1L, "Tenis", 120000);
        producto2 = new Producto(2L, "Reloj", 80000);
    }

    @Test
    void listarProductos() {
        List<Producto> listaSimulada = Arrays.asList(producto1, producto2);
        when(productoRepository.findAll()).thenReturn(listaSimulada);

        Flux<Producto> resultado = productoService.obtenerProductos();
        List<Producto> productosObtenidos = resultado.collectList().block();

        assertNotNull(productosObtenidos);
        assertEquals(2, productosObtenidos.size());
        assertEquals("Tenis", productosObtenidos.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void obtenerProductoPorId() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto1));

        Mono<Producto> resultado = productoService.obtenerProductoPorId(1L);
        Producto productoObtenido = resultado.block();

        assertNotNull(productoObtenido);
        assertEquals("Tenis", productoObtenido.getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void crearProducto() {
        Producto nuevoProducto = new Producto(null, "Bufanda", 30000);
        when(productoRepository.save(nuevoProducto)).thenReturn(new Producto(3L, "Bufanda", 30000));

        Mono<Producto> resultado = productoService.guardarProducto(nuevoProducto);
        Producto productoGuardado = resultado.block();

        assertNotNull(productoGuardado);
        assertEquals(3L, productoGuardado.getId());
        assertEquals("Bufanda", productoGuardado.getNombre());
        verify(productoRepository, times(1)).save(nuevoProducto);
    }
}