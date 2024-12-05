package com.sistema.contas.auth.infrastructures.services;

import com.grandle.auth.dtos.UsuarioDTO;
import com.sistema.contas.auth.adapters.converts.UsuarioConverter;
import com.sistema.contas.auth.applications.ports.respository.UsuarioRepository;
import com.sistema.contas.auth.applications.ports.services.IUsuarioService;
import com.sistema.contas.auth.domain.entities.Usuario;
import com.sistema.contas.auth.domain.exceptions.UsuarioNaoAutorizadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO obterUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioConverter::paraDTO)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException("Usuário não encontrado para o email: " + email));
    }

    @Override
    public void criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioConverter.paraEntidade(usuarioDTO);
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException("Usuário não encontrado para o ID: " + id));

        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());

        // Corrigido: Passando o usuarioExistente como segundo parâmetro
        usuarioExistente.setPerfis(UsuarioConverter.paraEntidadePerfis(usuarioDTO.getRoles(), usuarioExistente));

        usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void deletarUsuario(Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoAutorizadoException("Usuário não encontrado para o ID: " + id));

        usuarioRepository.delete(usuarioExistente);
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll(); // Busca todos os usuários no banco
        return usuarios.stream() // Converte cada usuário para DTO
                .map(UsuarioConverter::paraDTO)
                .collect(Collectors.toList()); // Retorna a lista de DTOs
    }
}
