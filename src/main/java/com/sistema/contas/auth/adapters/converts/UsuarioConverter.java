package com.sistema.contas.auth.adapters.converts;

import com.grandle.auth.dtos.UsuarioDTO;
import com.sistema.contas.auth.domain.entities.Usuario;
import com.sistema.contas.auth.domain.entities.UsuarioPerfil;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioConverter {

    public static UsuarioDTO paraDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(Math.toIntExact(usuario.getId()));
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setRoles(
                usuario.getPerfis() != null
                        ? usuario.getPerfis().stream()
                        .map(UsuarioPerfil::getDescricao) // Usando o campo descricao
                        .collect(Collectors.toList())
                        : List.of()
        );
        return usuarioDTO;
    }

        public static Usuario paraEntidade(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId() != null ? usuarioDTO.getId().longValue() : null); // Converte Integer para Long
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPerfis(
                usuarioDTO.getRoles() != null
                        ? usuarioDTO.getRoles().stream()
                        .map(role -> {
                            UsuarioPerfil perfil = new UsuarioPerfil();
                            perfil.setDescricao(role); // Converte role (String) para UsuarioPerfil
                            perfil.setUsuario(usuario); // Atribuindo corretamente o usuário
                            return perfil;
                        })
                        .collect(Collectors.toList())
                        : List.of()
        );
        return usuario;
    }

    // Método novo que converte a lista de roles (String) para lista de UsuarioPerfil
    public static List<UsuarioPerfil> paraEntidadePerfis(List<String> roles, Usuario usuario) {
        return roles != null
                ? roles.stream()
                .map(role -> {
                    UsuarioPerfil perfil = new UsuarioPerfil();
                    perfil.setDescricao(role); // Aqui, assumimos que o campo é "descricao"
                    perfil.setUsuario(usuario); // Atribuindo corretamente o usuário
                    return perfil;
                })
                .collect(Collectors.toList())
                : List.of();
    }
}
