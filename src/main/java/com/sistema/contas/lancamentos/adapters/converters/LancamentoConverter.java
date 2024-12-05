package com.sistema.contas.lancamentos.adapters.converters;

import com.grandle.lancamento.dtos.LancamentoDTO;
import com.sistema.contas.lancamentos.domain.entities.Lancamento;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.sistema.contas.lancamentos.domain.enums.TipoLancamento;
import org.springframework.stereotype.Component;

@Component
public class LancamentoConverter {

    private final LancamentoCategoriaConverter lancamentoCategoriaConverter;
    private final LancamentoPessoaConverter lancamentoPessoaConverter;

    public LancamentoConverter(LancamentoCategoriaConverter lancamentoCategoriaConverter, LancamentoPessoaConverter lancamentoPessoaConverter) {
        this.lancamentoCategoriaConverter = lancamentoCategoriaConverter;
        this.lancamentoPessoaConverter = lancamentoPessoaConverter;
    }

    public LancamentoDTO.LancamentoDTOBuilder criarDTOBase(Lancamento lancamento) {
        if (lancamento == null) {
            return null;
        }

        return LancamentoDTO.builder()
                .id(Math.toIntExact(lancamento.getId()))
                .descricao(lancamento.getDescricao())
                .dataNascimento(convertToLocalDate(lancamento.getDataNascimento()))
                .dataPagamento(convertToLocalDate(lancamento.getDataPagamento()))
                .valor(lancamento.getValor())
                .observacao(lancamento.getObservacao())
                .tipo(String.valueOf(lancamento.getTipo()))
                .categoria(lancamentoCategoriaConverter.toDTO(lancamento.getCategoria()))
                .pessoa(lancamentoPessoaConverter.toDTO(lancamento.getPessoa()));
    }

    // Novo método toEntity
    public Lancamento toEntity(LancamentoDTO lancamentoDTO) {
        if (lancamentoDTO == null) {
            return null;
        }

        Lancamento lancamento = new Lancamento();
        lancamento.setId(lancamentoDTO.getId() != null ? lancamentoDTO.getId().longValue() : null); // Converte Integer para Long
        lancamento.setDescricao(lancamentoDTO.getDescricao());
        lancamento.setValor(lancamentoDTO.getValor());
        lancamento.setDataPagamento(convertToDate(lancamentoDTO.getDataPagamento())); // Converte LocalDate para Date
        lancamento.setDataNascimento(convertToDate(lancamentoDTO.getDataNascimento())); // Converte LocalDate para Date
        lancamento.setTipo(lancamentoDTO.getTipo() != null ? Enum.valueOf(TipoLancamento.class, lancamentoDTO.getTipo()) : null); // Converte String para Enum
        lancamento.setObservacao(lancamentoDTO.getObservacao());
        lancamento.setCategoria(lancamentoCategoriaConverter.toEntity(lancamentoDTO.getCategoria())); // Converte CategoriaDTO para Categoria
        lancamento.setPessoa(lancamentoPessoaConverter.toEntity(lancamentoDTO.getPessoa())); // Converte PessoaDTO para Pessoa
        return lancamento;
    }

    // Método auxiliar para converter Date para LocalDate
    private LocalDate convertToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // Método auxiliar para converter LocalDate para Date
    private Date convertToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
