package com.sistema.contas.endereco.application.ports.repository;

import com.sistema.contas.endereco.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("SELECT e FROM Endereco e WHERE e.rua LIKE %:filtro% OR e.bairro LIKE %:filtro%")
    List<Endereco> listar(@Param("filtro") String filtro);
}
