package com.sistema.contas.lancamentos.infrastructure.repository;

import com.sistema.contas.lancamentos.applications.ports.repository.LancamentoRepository;
import com.sistema.contas.lancamentos.applications.ports.service.ILancamentoService;
import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import com.sistema.contas.lancamentos.domain.usecases.LancamentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LancamentoService implements ILancamentoService {

    private final LancamentoUseCase lancamentoUseCase;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoUseCase = new LancamentoUseCase(lancamentoRepository);
    }

    @Override
    public Page<Lancamento> listarLancamentosPaginados(int page, int size) {
        return lancamentoUseCase.listarLancamentosPaginados(page, size);
    }

    @Override
    public Page<Lancamento> listarLancamentosFiltrados(String descricao, LocalDate dataInicio, LocalDate dataFim, int page, int size) {
        return lancamentoUseCase.listarLancamentosFiltrados(descricao, dataInicio, dataFim, page, size);
    }

    @Override
    public Optional<Lancamento> obterLancamentoPorId(Long id) {
        return lancamentoUseCase.obterLancamentoPorId(id);
    }

    @Override
    public Lancamento criarLancamento(Lancamento lancamento) {
        return lancamentoUseCase.criarLancamento(lancamento);
    }

    @Override
    public Lancamento atualizarLancamento(Integer id, Lancamento lancamentoAtualizado) {
        return lancamentoUseCase.atualizarLancamento(id, lancamentoAtualizado);
    }

    @Override
    public void deletarLancamento(Long id) {
        lancamentoUseCase.deletarLancamento(id);
    }
}
