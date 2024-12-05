package com.sistema.contas.lancamentos.adapters.converters;

import com.grandle.lancamento.dtos.PessoaDTO;
import com.sistema.contas.pessoa.domain.entities.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class LancamentoPessoaConverter {

    private final LancamentoEnderecoConverter lancamentoEnderecoConverter;

    public LancamentoPessoaConverter(LancamentoEnderecoConverter lancamentoEnderecoConverter) {
        this.lancamentoEnderecoConverter = lancamentoEnderecoConverter;
    }

    // Converte a entidade Pessoa para o DTO PessoaDTO
    public PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        return PessoaDTO.builder()
                .id(pessoa.getId() != null ? Math.toIntExact(pessoa.getId()) : null) // Trata nulos
                .nome(pessoa.getNome())
                .ativo(pessoa.getAtivo())
                .endereco(pessoa.getEndereco() != null
                        ? lancamentoEnderecoConverter.toDto(pessoa.getEndereco())
                        : null) // Trata nulos no endereço
                .build();
    }

    // Converte o DTO PessoaDTO para a entidade Pessoa
    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.getId() != null ? Long.valueOf(pessoaDTO.getId()) : null);
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setAtivo(pessoaDTO.getAtivo());
        pessoa.setEndereco(pessoaDTO.getEndereco() != null
                ? lancamentoEnderecoConverter.toEntity(pessoaDTO.getEndereco())
                : null); // Trata nulos no endereço

        return pessoa;
    }
}
