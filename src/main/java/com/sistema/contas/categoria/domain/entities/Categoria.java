package com.sistema.contas.categoria.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "categoria", schema = "contas")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_categoria")
    @SequenceGenerator(name = "sq_categoria", sequenceName = "contas.sq_categoria", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser nulo")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
}
