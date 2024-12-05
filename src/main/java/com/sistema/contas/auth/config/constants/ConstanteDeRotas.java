package com.sistema.contas.auth.config.constants;

import java.util.List;

public class ConstanteDeRotas {

    // Rotas livres que não requerem autenticação (exemplo: login, logout)
    public static final List<String> ROTAS_LIVRES = List.of(
            "/auth/login",     // Rota para realizar login
            "/auth/logout",    // Rota para realizar logout
            "/auth/session"    // Rota para obter dados da sessão
    );

    // Rota para login (separado porque pode ser mais específico)
    public static final String ROTAS_PARA_LOGIN = "/auth/login";

    // Rotas para o gerenciamento de usuários
    public static final List<String> ROTAS_USUARIOS = List.of(
            "/usuarios",           // Rota para listar todos os usuários (GET)
            "/usuarios/{id}",      // Rota para obter um usuário específico (GET)
            "/usuarios/{id}/update", // Rota para atualizar um usuário (PUT)
            "/usuarios/{id}/delete"  // Rota para deletar um usuário (DELETE)
    );
}
