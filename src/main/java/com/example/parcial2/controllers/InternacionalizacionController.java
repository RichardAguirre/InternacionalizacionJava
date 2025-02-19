package com.example.parcial2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@RestController
public class InternacionalizacionController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public String obtenerSaludo(@RequestHeader(name = "Accept-Language", required = false) String header) {
        Locale locale = header != null && header.startsWith("es") ? new Locale("es") : Locale.ENGLISH;
        /*Locale locale = new Locale("en");*/
        return messageSource.getMessage("welcome.message", null, locale);
    }
}