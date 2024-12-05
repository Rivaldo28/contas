package com.sistema.contas.pessoa.application.ports.repository;

import com.sistema.contas.pessoa.domain.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT p FROM Pessoa p WHERE (:filtro IS NULL OR p.nome LIKE :filtro) AND (:ativo IS NULL OR p.ativo = :ativo)")
    Page<Pessoa> findAllByFiltroAndAtivo(@Param("filtro") String filtro, @Param("ativo") Boolean ativo, Pageable pageable);

}
