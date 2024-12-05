package com.sistema.contas.lancamentos.domain.entities;

import com.sistema.contas.categoria.domain.entities.Categoria;
import com.sistema.contas.pessoa.domain.entities.Pessoa;
import com.sistema.contas.lancamentos.domain.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "lancamento", schema = "contas")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_lancamento")
    @SequenceGenerator(name = "sq_lancamento", sequenceName = "contas.sq_lancamento", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @NotBlank(message = "O id não pode ser vazio")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O descrição não pode ser vazio")
    private String descricao;

    @Column(nullable = false)
    @NotBlank(message = "A data não pode ser vazia")
    private Date dataNascimento;

    @Column(nullable = false)
    @NotBlank(message = "A data pagamento não pode ser vazio")
    private Date DataPagamento;

    @Column(nullable = false)
    @NotBlank(message = "O valor não pode ser nulo")
    private BigDecimal valor;

    @Column(nullable = false)
    @NotBlank(message = "Escreva uma observção")
    private String observacao;

    @NotNull(message = "O tipo não pode nulo")
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categoria")
    @NotNull(message = "O campo 'endereco' não pode ser nulo")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_pessoa")
    @NotNull(message = "O campo 'pessoa' não pode ser nulo")
    private Pessoa pessoa;

}
