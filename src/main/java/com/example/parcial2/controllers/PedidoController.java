package com.example.parcial2.controllers;

import com.example.parcial2.models.Pedido;
import com.example.parcial2.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Locale;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Mono<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @GetMapping
    public Flux<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/mensaje")
    public Mono<String> obtenerMensaje(@RequestParam(name = "lang", defaultValue = "es") String lang) {
        Locale locale = "en".equals(lang) ? Locale.ENGLISH : new Locale("es");
        return Mono.just(pedidoService.obtenerMensaje("pedido.mensaje", locale));
    }
}