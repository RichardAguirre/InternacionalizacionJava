package com.example.parcial2.services;

import com.example.parcial2.models.Usuario;
import com.example.parcial2.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.justOrEmpty(usuarioRepository.findByUsername(username))
                .map(usuario -> User.withUsername(usuario.getUsername())
                        .password(usuario.getPassword())
                        .roles(usuario.getRol())
                        .build());
    }
}