package com.sistema.contas.pessoa.adapters.controllers;

import com.grandle.pessoa.controllers.PessoaApi;
import com.grandle.pessoa.dtos.ListaPessoasResponse;
import com.grandle.pessoa.dtos.PessoaDTO;
import com.sistema.contas.pessoa.adapters.converters.PessoaConverters;
import com.sistema.contas.pessoa.application.ports.service.IPessoaService;
import com.sistema.contas.pessoa.domain.entities.Pessoa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PessoaController implements PessoaApi {

    private final IPessoaService pessoaService;

    @Override
    public ResponseEntity<PessoaDTO> adicionarPessoa(PessoaDTO pessoaDTO) throws Exception {
        // Converter o DTO para a entidade Pessoa
        Pessoa pessoa = PessoaConverters.toEntity(pessoaDTO);

        // Chama o serviço para salvar a pessoa
        Pessoa pessoaSalva = pessoaService.criarPessoa(pessoa);

        // Converte a entidade salva para DTO e retorna a resposta
        PessoaDTO pessoaDTOResponse = PessoaConverters.toDto(pessoaSalva);
        return ResponseEntity.status(201).body(pessoaDTOResponse);
    }

    @Override
    public ResponseEntity<PessoaDTO> atualizarPessoa(Integer id, PessoaDTO pessoaDTO) throws Exception {
        // Converte o DTO para a entidade Pessoa
        Pessoa pessoa = PessoaConverters.toEntity(pessoaDTO);

        // Chama o serviço para atualizar a pessoa com o ID fornecido
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(Long.valueOf(id), pessoa);

        // Converte a entidade atualizada para DTO e retorna a resposta
        PessoaDTO pessoaDTOResponse = PessoaConverters.toDto(pessoaAtualizada);
        return ResponseEntity.ok(pessoaDTOResponse);
    }

    @Override
    public ResponseEntity<ListaPessoasResponse> listarPessoas(String nome, Boolean ativo, Integer page, Integer size) throws Exception {
        // Definindo valores padrão para paginação e ordenação, caso não sejam fornecidos
        int pageNum = (page != null) ? page : 0;  // Página 0 por padrão
        int pageSize = (size != null) ? size : 20; // Tamanho 20 por padrão
        String sortBy = "nome";  // Ordenação por nome como padrão
        String direction = "asc"; // Ordem ascendente por padrão

        // Definindo a direção da ordenação
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);

        // Criando o Pageable com a ordenação
        PageRequest pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortDirection, sortBy));

        // Chama o serviço para listar as pessoas com os parâmetros de paginação e ordenação
        Page<Pessoa> pessoasPage = pessoaService.listarPessoas(nome, ativo, pageNum, pageSize, sortBy, direction);

        // Converte as entidades de Pessoa para DTOs
        List<PessoaDTO> pessoasDTO = pessoasPage.getContent().stream()
                .map(PessoaConverters::toDto)
                .collect(Collectors.toList());

        // Cria o objeto de resposta com a paginação
        ListaPessoasResponse response = new ListaPessoasResponse();
        response.setContent(pessoasDTO);
        response.setTotalElements((int) pessoasPage.getTotalElements());  // Contagem total de pessoas
        response.setTotalPages(pessoasPage.getTotalPages());  // Total de páginas
        response.setPageable(null); // Não envia o Pageable se não for necessário

        return ResponseEntity.ok(response);  // Retorna a resposta com status 200 (OK)
    }

    @Override
    public ResponseEntity<PessoaDTO> obterPessoa(Integer id) throws Exception {
        // Chama o serviço para obter a pessoa pelo ID
        Pessoa pessoa = pessoaService.obterPessoaPorId(Long.valueOf(id));

        if (pessoa == null) {
            return ResponseEntity.notFound().build();  // Retorna 404 caso a pessoa não seja encontrada
        }

        // Converte a entidade para DTO e retorna a resposta
        PessoaDTO pessoaDTOResponse = PessoaConverters.toDto(pessoa);
        return ResponseEntity.ok(pessoaDTOResponse);
    }

    @Override
    public ResponseEntity<Void> removerPessoa(Integer id) throws Exception {
        // Chama o serviço para remover a pessoa pelo ID
        pessoaService.removerPessoa(Long.valueOf(id));

        // Retorna resposta de sucesso com status 204 (sem conteúdo)
        return ResponseEntity.noContent().build();
    }
}
