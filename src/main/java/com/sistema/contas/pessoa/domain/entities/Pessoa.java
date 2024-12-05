package com.sistema.contas.pessoa.domain.entities;

import com.sistema.contas.endereco.domain.entities.Endereco;
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
@Table(name = "pessoa", schema = "contas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pessoa")
    @SequenceGenerator(name = "sq_pessoa", sequenceName = "contas.sq_pessoa", allocationSize = 1)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O campo 'ativo' não pode ser nulo")
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) // Incluí CascadeType.PERSIST para persistir Endereco automaticamente
    @JoinColumn(name = "id_endereco", nullable = false) // Define a coluna de chave estrangeira
    @NotNull(message = "O campo 'endereco' não pode ser nulo") // Validações adicionais
    private Endereco endereco;
}
