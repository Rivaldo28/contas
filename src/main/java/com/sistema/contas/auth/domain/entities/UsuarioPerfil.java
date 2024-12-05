package com.sistema.contas.auth.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_perfil", schema = "contas")
public class UsuarioPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
    @SequenceGenerator(name = "sq_perfil", sequenceName = "contas.sq_perfil", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "A descrição do perfil não pode ser vazia")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public String getDescricao() {
        return descricao;
    }
}
