package com.sistema.contas.endereco.domain.entities;

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
@Table(name = "endereco", schema = "contas")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_endereco")
    @SequenceGenerator(name = "sq_endereco", sequenceName = "contas.sq_endereco", allocationSize = 1)
    @Column(nullable = false)
    @NotNull(message = "O id não pode ser vazio")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode ser vazia")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "A rua não pode ser vazio")
    private String rua;

    @Column(nullable = false)
    @NotBlank(message = "O número não pode ser vazio")
    private String numero;

    @Column(nullable = false)
    @NotBlank(message = "O complemento não pode ser vazio")
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "O bairro não pode ser vazio")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "A cidade não pode ser vazia")
    private String cidade;

    @Column(nullable = false)
    @NotBlank(message = "O estado não pode ser vazio")
    private String estado;

    @Column(nullable = false)
    @NotBlank(message =  "O cep não pode ser vazio")
    private String cep;

}
