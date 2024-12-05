package com.sistema.contas.lancamentos.applications.ports.service;

import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


public interface ILancamentoService {

    Page<Lancamento> listarLancamentosPaginados(int page, int size);

    // Método para listar lançamentos com filtros aplicados e paginação
    @Transactional(readOnly = true)
    Page<Lancamento> listarLancamentosFiltrados(
            String descricao,
            LocalDate dataInicio,
            LocalDate dataFim,
            int page,
            int size);

    Optional<Lancamento> obterLancamentoPorId(Long id);

    Lancamento criarLancamento(Lancamento lancamento);

    Lancamento atualizarLancamento(Integer id, Lancamento lancamentoAtualizado);

    void deletarLancamento(Long id);
}
