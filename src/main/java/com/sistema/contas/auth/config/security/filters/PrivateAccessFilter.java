package com.sistema.contas.auth.config.security.filters;

import com.sistema.contas.auth.config.constants.ConstanteDeRotas;
import com.sistema.contas.auth.infrastructures.services.UsuarioService;
import com.sistema.contas.auth.infrastructures.services.TokenService;  // Corrigido para importar seu TokenService
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class PrivateAccessFilter extends OncePerRequestFilter {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;  // Corrigido para usar seu TokenService

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        String authorizationHeader = request.getHeader("Authorization");

        // Se a rota for livre, libera o acesso
        if (ConstanteDeRotas.ROTAS_LIVRES.stream().anyMatch(requestUri::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Se for a rota de login, verifica o token de autenticação
        if (requestUri.equals(ConstanteDeRotas.ROTAS_PARA_LOGIN)) {
            if(!tokenPresente(authorizationHeader)){
                tratarAcessoNaoAutorizado(response, requestUri, requestUri, requestMethod);
            } else {
                try {
                    // Aqui, agora estamos extraindo o e-mail ao invés de CPF
                    String email = tokenService.extrairEmailDoToken(authorizationHeader);  // Alterado para email
                    usuarioService.obterUsuarioPorEmail(email);  // Alterado para método que usa email
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    // Caso haja algum erro ao tentar obter dados do usuário, retorna erro
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.setContentType("application/json");
                    response.getWriter().write(e.getMessage());
                    response.getWriter().flush();
                }
            }
        } else {
            filterChain.doFilter(request, response);  // Para outras rotas, apenas continua o filtro
        }
    }

    // Verifica se o token está presente no cabeçalho de autorização
    private boolean tokenPresente(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }

    // Tratar erro de acesso não autorizado
    private void tratarAcessoNaoAutorizado(HttpServletResponse response, String requestUri, String requestUrl, String httpMethod) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Acesso negado: Token de autenticação é obrigatório para acessar a URL: " + requestUri + " | " + requestUrl + " [" + httpMethod + "]");

        log.error("Acesso negado: Token de autenticação é obrigatório para acessar a URL completa: " + requestUri + " | " + requestUrl
                + " com o método HTTP: " + httpMethod);
    }
}
