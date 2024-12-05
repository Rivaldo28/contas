package com.sistema.contas.categoria.applications.ports.repository;

import com.sistema.contas.categoria.domain.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
