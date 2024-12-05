package com.sistema.contas.auth.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_perfil", schema = "contas")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
    @SequenceGenerator(name = "sq_perfil", sequenceName = "contas.sq_perfil", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser nulo")
    private Long id;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "A descrição do perfil não pode ser vazia")
    private String descricao;
}
