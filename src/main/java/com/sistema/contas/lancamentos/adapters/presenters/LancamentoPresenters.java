package com.sistema.contas.lancamentos.adapters.presenters;

import com.grandle.lancamento.dtos.ListaLancamentosResponse;
import com.grandle.lancamento.dtos.LancamentoDTO;
import com.sistema.contas.lancamentos.adapters.converters.LancamentoConverter;
import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LancamentoPresenters {

    private final LancamentoConverter lancamentoConverter;

    public LancamentoPresenters(LancamentoConverter lancamentoConverter) {
        this.lancamentoConverter = lancamentoConverter;
    }

    public ListaLancamentosResponse paraResponse(Page<Lancamento> lancamentoPage) {
        if (lancamentoPage == null || lancamentoPage.isEmpty()) {
            return ListaLancamentosResponse.builder()
                    .content(null)
                    .totalElements(0) // Ajuste de tipo para Integer
                    .totalPages(0)
                    .first(true)
                    .last(false)
                    .empty(true)
                    .pageable(null) // Não temos informações de paginação sem Page
                    .build();
        }

        return ListaLancamentosResponse.builder()
                .content(lancamentoPage.getContent()
                        .stream()
                        .map(lancamentoConverter::criarDTOBase) // Usa o método da instância
                        .map(LancamentoDTO.LancamentoDTOBuilder::build) // Constrói os objetos DTO
                        .collect(Collectors.toList()))
                .totalElements((int) lancamentoPage.getTotalElements()) // Converte long para Integer
                .totalPages(lancamentoPage.getTotalPages())
                .first(lancamentoPage.isFirst())
                .last(lancamentoPage.isLast())
                .empty(lancamentoPage.isEmpty())
                .pageable(null) // Preencha se necessário usando os dados do objeto Page
                .build();
    }

}
