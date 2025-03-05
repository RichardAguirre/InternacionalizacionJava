package com.example.parcial2.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public Mono<String> obtenerSaludo(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        Locale locale = request.getHeaders().getAcceptLanguage().stream()
                .findFirst()
                .map(Locale.LanguageRange::getRange)
                .map(Locale::forLanguageTag)
                .orElse(Locale.ENGLISH);

        return Mono.just(messageSource.getMessage("saludo", null, locale));
    }
}