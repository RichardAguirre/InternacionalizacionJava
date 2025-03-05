package com.example.parcial2.services;

import com.example.parcial2.models.Pedido;
import com.example.parcial2.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Locale;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MessageSource messageSource;

    public Mono<Pedido> crearPedido(Pedido pedido) {
        return Mono.just(pedidoRepository.save(pedido));
    }

    public Flux<Pedido> listarPedidos() {
        return Flux.fromIterable(pedidoRepository.findAll());
    }

    public String obtenerMensaje(String clave, Locale locale) {
        return messageSource.getMessage(clave, null, locale);
    }
}