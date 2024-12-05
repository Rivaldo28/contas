package com.sistema.contas.endereco.adapters.controllers;

import com.grandle.endereco.controllers.EnderecoApi;
import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.application.ports.service.IEnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EnderecoController implements EnderecoApi {

    private final IEnderecoService enderecoService; // Injeta o serviço de endereço

    // Listar endereços com possíveis filtros
    @Override
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos(String logradouro, String bairro, String cidade, String estado) throws Exception {
        // Montar um filtro combinando os parâmetros fornecidos
        String filtro = "";
        if (logradouro != null) filtro += logradouro + " ";
        if (bairro != null) filtro += bairro + " ";
        if (cidade != null) filtro += cidade + " ";
        if (estado != null) filtro += estado;

        List<EnderecoDTO> enderecos = enderecoService.listarEnderecos(filtro.trim());
        return ResponseEntity.ok(enderecos); // Retorna a lista de endereços filtrados
    }

    // Obter endereço pelo ID
    @Override
    public ResponseEntity<EnderecoDTO> obterEndereco(Integer id) throws Exception {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Retorna erro se o ID for inválido
        }

        EnderecoDTO endereco = enderecoService.obterEndereco(Long.valueOf(id));
        if (endereco == null) {
            return ResponseEntity.notFound().build(); // Retorna erro 404 se o endereço não existir
        }

        return ResponseEntity.ok(endereco); // Retorna o endereço encontrado
    }

    // Criar um novo endereço
    @Override
    public ResponseEntity<EnderecoDTO> criarEndereco(EnderecoDTO enderecoDTO) throws Exception {
        if (enderecoDTO == null) {
            return ResponseEntity.badRequest().build(); // Retorna erro se o corpo da requisição estiver vazio
        }

        EnderecoDTO criado = enderecoService.criarEndereco(enderecoDTO); // Chama o serviço para criar o endereço
        return ResponseEntity.status(HttpStatus.CREATED).body(criado); // Retorna 201 com o objeto criado
    }

    // Atualizar um endereço existente
    @Override
    public ResponseEntity<EnderecoDTO> atualizarEndereco(Integer id, EnderecoDTO enderecoDTO) throws Exception {
        if (id == null || id <= 0 || enderecoDTO == null) {
            return ResponseEntity.badRequest().build(); // Retorna erro se o ID ou DTO forem inválidos
        }

        EnderecoDTO atualizado = enderecoService.atualizarEndereco(Long.valueOf(id), enderecoDTO); // Chama o serviço para atualizar o endereço
        if (atualizado == null) {
            return ResponseEntity.notFound().build(); // Retorna erro 404 se o endereço não existir
        }

        return ResponseEntity.ok(atualizado); // Retorna o endereço atualizado
    }

    // Remover um endereço pelo ID
    @Override
    public ResponseEntity<Void> removerEndereco(Integer id) throws Exception {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Retorna erro se o ID for inválido
        }

        enderecoService.removerEndereco(Long.valueOf(id)); // Chama o serviço para remover o endereço
        return ResponseEntity.noContent().build(); // Retorna 204 após remoção bem-sucedida
    }
}
