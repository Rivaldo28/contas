package com.sistema.contas.lancamentos.adapters.controllers;

import com.grandle.lancamento.controllers.LancamentoApi;
import com.grandle.lancamento.dtos.LancamentoDTO;
import com.grandle.lancamento.dtos.ListaLancamentosResponse;
import com.sistema.contas.lancamentos.adapters.converters.LancamentoConverter;
import com.sistema.contas.lancamentos.applications.ports.service.ILancamentoService;
import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lancamentos")
public class LancamentoController implements LancamentoApi {

    private final ILancamentoService lancamentoService;
    private final LancamentoConverter lancamentoConverter;

    @Override
    public ResponseEntity<LancamentoDTO> adicionarLancamento(LancamentoDTO lancamentoDTO) throws Exception {
        Lancamento lancamento = lancamentoConverter.toEntity(lancamentoDTO); // Converter DTO para entidade
        Lancamento novoLancamento = lancamentoService.criarLancamento(lancamento); // Criar lançamento
        LancamentoDTO lancamentoCriadoDTO = lancamentoConverter.criarDTOBase(novoLancamento).build(); // Converter para DTO
        return ResponseEntity.ok(lancamentoCriadoDTO); // Retornar resposta
    }

    @Override
    public ResponseEntity<LancamentoDTO> atualizarLancamento(Integer id, LancamentoDTO lancamentoDTO) throws Exception {
        Lancamento lancamento = lancamentoConverter.toEntity(lancamentoDTO); // Converter DTO para entidade
        Lancamento lancamentoAtualizado = lancamentoService.atualizarLancamento(id, lancamento); // Atualizar lançamento
        LancamentoDTO lancamentoAtualizadoDTO = lancamentoConverter.criarDTOBase(lancamentoAtualizado).build(); // Converter para DTO
        return ResponseEntity.ok(lancamentoAtualizadoDTO); // Retornar resposta
    }

    @Override
    @GetMapping("/lancamentos")
    public ResponseEntity<ListaLancamentosResponse> listarLancamentos(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) LocalDate dataFim,
            @RequestParam(value = "page", defaultValue = "0") Integer page,  // Parâmetro de página com valor padrão
            @RequestParam(value = "size", defaultValue = "10") Integer size   // Parâmetro de tamanho com valor padrão
    ) throws Exception {
        // Verificar se os parâmetros estão sendo passados corretamente
        System.out.println("Page: " + page + ", Size: " + size);

        var lancamentosPage = lancamentoService.listarLancamentosFiltrados(descricao, dataInicio, dataFim, page, size); // Obter lançamentos paginados
        ListaLancamentosResponse response = ListaLancamentosResponse.builder()
                .content(lancamentosPage.getContent().stream()
                        .map(lancamento -> lancamentoConverter.criarDTOBase(lancamento).build()) // Converter cada entidade para DTO
                        .toList())
                .totalElements((int) lancamentosPage.getTotalElements())
                .totalPages(lancamentosPage.getTotalPages())
                .first(lancamentosPage.isFirst())
                .last(lancamentosPage.isLast())
                .empty(lancamentosPage.isEmpty())
                .build();
        return ResponseEntity.ok(response); // Retornar resposta
    }

    @Override
    public ResponseEntity<LancamentoDTO> obterLancamento(Integer id) throws Exception {
        Optional<Lancamento> lancamentoOptional = lancamentoService.obterLancamentoPorId(Long.valueOf(id)); // Buscar lançamento
        if (lancamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retornar 404 se não encontrado
        }
        LancamentoDTO lancamentoDTO = lancamentoConverter.criarDTOBase(lancamentoOptional.get()).build(); // Converter para DTO
        return ResponseEntity.ok(lancamentoDTO); // Retornar resposta
    }

    @Override
    public ResponseEntity<Void> removerLancamento(Integer id) throws Exception {
        lancamentoService.deletarLancamento(Long.valueOf(id)); // Deletar lançamento
        return ResponseEntity.noContent().build(); // Retornar resposta 204
    }
}
