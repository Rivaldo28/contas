package com.sistema.contas.auth.applications.ports.services;

import com.grandle.auth.dtos.UsuarioDTO;
import java.util.List;

public interface IUsuarioService {
    UsuarioDTO obterUsuarioPorEmail(String email);
    void criarUsuario(UsuarioDTO usuarioDTO);
    void atualizarUsuario(Long id, UsuarioDTO usuarioDTO);
    void deletarUsuario(Long id);
    List<UsuarioDTO> listarUsuarios();  // Método adicionado
}
