package com.example.parcial2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.server.i18n.FixedLocaleContextResolver;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleContextResolver localeContextResolver() {
        return new FixedLocaleContextResolver(Locale.ENGLISH);
    }
}