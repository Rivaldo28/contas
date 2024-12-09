package com.sistema.contas.auth.infrastructures.services;

import com.sistema.contas.auth.applications.ports.respository.UsuarioRepository;
import com.sistema.contas.auth.domain.entities.Usuario;
import com.sistema.contas.auth.domain.exceptions.UsuarioNaoAutorizadoException;
import com.sistema.contas.core.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtDecoder jwtDecoder;
    private final UsuarioRepository usuarioRepository;

    public String extrairEmailDoToken(String authorizationHeader) {
        String jwtToken = extrairTokenDoHeader(authorizationHeader);
        Jwt jwt = jwtDecoder.decode(jwtToken);
        return JWTUtils.obterEmail(jwt);
    }

    public String extrairEmailDoAuthentication(Authentication authentication) {
        String jwtToken = extrairTokenDoAuthentication(authentication);
        Jwt jwt = jwtDecoder.decode(jwtToken);
        return JWTUtils.obterEmail(jwt);
    }

    private String extrairTokenDoHeader(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        throw new IllegalArgumentException("Token JWT inválido ou ausente");
    }

    private String extrairTokenDoAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getTokenValue();
        }
        throw new IllegalStateException("O principal não contém um objeto Jwt.");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getUsuarioId(String email) {
        return usuarioRepository.findByEmail(email)
                .map(Usuario::getId)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException(
                        String.format("Usuário com e-mail %s não encontrado ou sem permissões ativas.", email)
                ));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException(
                        String.format("Usuário com e-mail %s não encontrado ou sem permissões ativas.", email)
                ));
    }
}
