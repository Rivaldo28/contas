package com.sistema.contas.lancamentos.domain.usecases;

import com.sistema.contas.lancamentos.applications.ports.repository.LancamentoRepository;
import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import com.sistema.contas.lancamentos.domain.specifications.LancamentoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


public class LancamentoUseCase {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoUseCase(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    // Listar lançamentos paginados
    @Transactional(readOnly = true)
    public Page<Lancamento> listarLancamentosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return lancamentoRepository.findAll(pageable);
    }

    // Listar lançamentos com filtros
    @Transactional(readOnly = true)
    public Page<Lancamento> listarLancamentosFiltrados(String descricao, LocalDate dataInicio, LocalDate dataFim, int page, int size) {
        Specification<Lancamento> spec = LancamentoSpecification.filterByParams(descricao, dataInicio, dataFim);
        Pageable pageable = PageRequest.of(page, size);
        return lancamentoRepository.findAll(spec, pageable);
    }

    // Obter um lançamento por ID
    @Transactional(readOnly = true)
    public Optional<Lancamento> obterLancamentoPorId(Long id) {
        return lancamentoRepository.findById(id);
    }

    // Criar um novo lançamento
    @Transactional
    public Lancamento criarLancamento(Lancamento lancamento) {
        validarLancamento(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    // Atualizar um lançamento
    @Transactional
    public Lancamento atualizarLancamento(Integer id, Lancamento lancamentoAtualizado) {
        validarLancamento(lancamentoAtualizado);
        return lancamentoRepository.findById(Long.valueOf(id)).map(lancamento -> {
            lancamento.setDescricao(lancamentoAtualizado.getDescricao());
            lancamento.setValor(lancamentoAtualizado.getValor());
            lancamento.setDataPagamento(lancamentoAtualizado.getDataPagamento());
            lancamento.setTipo(lancamentoAtualizado.getTipo());
            lancamento.setCategoria(lancamentoAtualizado.getCategoria());
            lancamento.setPessoa(lancamentoAtualizado.getPessoa());
            return lancamentoRepository.save(lancamento);
        }).orElseThrow(() -> new RuntimeException("Lançamento não encontrado para o ID: " + id));
    }

    // Deletar um lançamento
    @Transactional
    public void deletarLancamento(Long id) {
        if (!lancamentoRepository.existsById(id)) {
            throw new RuntimeException("Lançamento não encontrado para o ID: " + id);
        }
        lancamentoRepository.deleteById(id);
    }

    // Método de validação
    private void validarLancamento(Lancamento lancamento) {
        if (lancamento.getDescricao() == null || lancamento.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição do lançamento é obrigatória.");
        }
        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do lançamento deve ser maior que zero.");
        }
        if (lancamento.getDataPagamento() == null) {
            throw new IllegalArgumentException("A data de pagamento é obrigatória.");
        }
        if (lancamento.getTipo() == null || lancamento.getTipo().toString().isEmpty()) {
            throw new IllegalArgumentException("O tipo do lançamento é obrigatório.");
        }
    }
}
