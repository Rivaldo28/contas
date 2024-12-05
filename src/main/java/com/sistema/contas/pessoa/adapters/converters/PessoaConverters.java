package com.sistema.contas.pessoa.adapters.converters;

import com.grandle.pessoa.dtos.EnderecoDTO;
import com.grandle.pessoa.dtos.PessoaDTO;
import com.sistema.contas.pessoa.domain.entities.Pessoa;
import com.sistema.contas.endereco.adapters.converters.EnderecoConverter;


public class PessoaConverters {

    public static PessoaDTO toDto(Pessoa entity) {
        if (entity == null) {
            return null;
        }

        // Converte a entidade Endereco para o EnderecoDTO correto
        EnderecoDTO enderecoDTO = PessoaEnderecoConverters.toDto(entity.getEndereco());

        return new PessoaDTO(
                entity.getId() != null ? entity.getId().intValue() : null, // Converte Long para Integer
                entity.getNome(),
                entity.getAtivo(),
                enderecoDTO // Usando o EnderecoDTO do pacote correto
        );
    }

    public static Pessoa toEntity(PessoaDTO dto) {
        if (dto == null) {
            return null;
        }

        // Converte o EnderecoDTO para Endereco
        var endereco = EnderecoConverter.toEntity(dto.getEndereco());

        return Pessoa.builder()
                .id(dto.getId() != null ? dto.getId().longValue() : null) // Converte Integer para Long
                .nome(dto.getNome())
                .ativo(dto.getAtivo())
                .endereco(endereco) // Atribui o Endereco convertido
                .build();
    }
}
