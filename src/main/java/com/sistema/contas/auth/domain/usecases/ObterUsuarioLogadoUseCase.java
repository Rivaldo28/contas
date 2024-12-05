package com.sistema.contas.auth.domain.usecases;

import com.grandle.auth.dtos.UsuarioDTO;
import com.sistema.contas.auth.adapters.converts.UsuarioConverter;
import com.sistema.contas.auth.applications.ports.respository.UsuarioRepository;
import com.sistema.contas.auth.domain.exceptions.UsuarioNaoAutorizadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ObterUsuarioLogadoUseCase {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO executar(String email) {
        var usuario = usuarioRepository.findByEmail(email).
                orElseThrow(() -> new UsuarioNaoAutorizadoException("Nenhum usuário válido com o email informado foi encontrado: " + email));
        return UsuarioConverter.paraDTO(usuario);
    }
}
