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

    /**
     * Extrai o e-mail do token JWT a partir do cabeçalho de autorização.
     *
     * @param authorizationHeader Cabeçalho de autorização contendo o token JWT.
     * @return O e-mail extraído do token.
     */
    public String extrairEmailDoToken(String authorizationHeader) {
        String jwtToken = extrairTokenDoHeader(authorizationHeader);
        Jwt jwt = jwtDecoder.decode(jwtToken);
        String email = JWTUtils.obterEmail(jwt);  // Método para extrair o e-mail
        return email;
    }

    /**
     * Extrai o e-mail do token JWT a partir da autenticação.
     *
     * @param authentication A autenticação contendo o token JWT.
     * @return O e-mail extraído do token.
     */
    public String extrairEmailDoAuthentication(Authentication authentication) {
        String jwtToken = extrairTokenDoAuthentication(authentication);
        Jwt jwt = jwtDecoder.decode(jwtToken);
        String email = JWTUtils.obterEmail(jwt);  // Método para extrair o e-mail
        return email;
    }

    /**
     * Extrai o token JWT do cabeçalho de autorização.
     *
     * @param authorization Cabeçalho de autorização.
     * @return O token JWT extraído.
     */
    private String extrairTokenDoHeader(String authorization) {
        if (authorization != null) {
            if (!authorization.startsWith("Bearer ")) {
                return authorization;
            }
            return authorization.substring(7);  // Remove "Bearer "
        }
        throw new IllegalArgumentException("Token JWT inválido ou ausente");
    }

    /**
     * Extrai o token JWT a partir da autenticação.
     *
     * @param authentication A autenticação contendo o token JWT.
     * @return O token JWT extraído.
     */
    private String extrairTokenDoAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getTokenValue();
        }
        throw new IllegalStateException("O principal não contém um objeto Jwt.");
    }

    /**
     * Recupera o ID do usuário a partir do e-mail extraído do token JWT.
     *
     * @param email O e-mail do usuário.
     * @return O ID do usuário.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long getUsuarioId(String email) {
        return usuarioRepository.findByEmail(email)
                .map(Usuario::getId)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException(
                        String.format("Usuário com e-mail %s não encontrado ou sem permissões ativas.", email)
                ));
    }

    /**
     * Recupera o usuário a partir do e-mail extraído do token JWT.
     *
     * @param email O e-mail do usuário.
     * @return O usuário.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Usuario getUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException(
                        String.format("Usuário com e-mail %s não encontrado ou sem permissões ativas.", email)
                ));
    }
}
