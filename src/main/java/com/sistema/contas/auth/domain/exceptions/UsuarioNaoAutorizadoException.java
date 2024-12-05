package com.sistema.contas.auth.domain.exceptions;


public class UsuarioNaoAutorizadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsuarioNaoAutorizadoException() {
        super("Usuário não autorizado para acessar este recurso.");
    }

    public UsuarioNaoAutorizadoException(String message) {
        super(message);
    }
}
