package com.sistema.contas.auth.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // Acessa o claim "roles" do JWT que contém os papéis do usuário
        List<String> roles = jwt.getClaimAsStringList("roles");

        // Se o claim "roles" não for encontrado ou estiver vazio, lançar uma exceção
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("Usuário não tem roles atribuídos.");
        }

        // Converte os roles para SimpleGrantedAuthority (autoridades)
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
