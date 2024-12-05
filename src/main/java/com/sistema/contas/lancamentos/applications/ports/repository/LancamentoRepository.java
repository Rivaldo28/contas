package com.sistema.contas.lancamentos.applications.ports.repository;

import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>,
        JpaSpecificationExecutor<Lancamento>, PagingAndSortingRepository<Lancamento, Long> {
}
