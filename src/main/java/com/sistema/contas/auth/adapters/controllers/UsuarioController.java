package com.sistema.contas.auth.adapters.controllers;

import com.grandle.auth.controllers.UsuariosApi;
import com.grandle.auth.dtos.UsuarioCreateDTO;
import com.grandle.auth.dtos.UsuarioDTO;
import com.sistema.contas.auth.applications.ports.services.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController implements UsuariosApi {

    private final IUsuarioService usuarioService;  // Usando a injeção de dependência correta

    @Override
    public ResponseEntity<Void> criarUsuario(String authorization, UsuarioDTO usuarioCreateDTO) throws Exception {
        try {
            usuarioService.criarUsuario(usuarioCreateDTO); // Passando o DTO de criação
            return new ResponseEntity<>(HttpStatus.CREATED); // Retorna 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna 400 em caso de erro
        }
    }

    @Override
    public ResponseEntity<Void> atualizarUsuario(Integer id, String authorization, UsuarioDTO usuarioCreateDTO) throws Exception {
        try {
            usuarioService.atualizarUsuario(id.longValue(), usuarioCreateDTO); // Passando o DTO de criação
            return new ResponseEntity<>(HttpStatus.OK); // Retorna 200 OK se atualizado com sucesso
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o usuário não for encontrado
        }
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(Integer id, String authorization) throws Exception {
        try {
            usuarioService.deletarUsuario(id.longValue()); // Chama o serviço para deletar o usuário
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content após deletar
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o usuário não for encontrado
        }
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(String authorization) throws Exception {
        try {
            List<UsuarioDTO> usuarios = usuarioService.listarUsuarios(); // Chama o serviço para listar os usuários
            return new ResponseEntity<>(usuarios, HttpStatus.OK); // Retorna 200 OK com a lista de usuários
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 em caso de erro interno
        }
    }
}
