package com.example.parcial2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public String obtenerSaludo(@RequestParam(name = "lang", defaultValue = "es") String lang) {
        Locale locale = "en".equals(lang) ? Locale.ENGLISH : new Locale("es");
        return messageSource.getMessage("saludo", null, locale);
    }
}