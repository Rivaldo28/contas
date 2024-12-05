package com.sistema.contas.pessoa.adapters.converters;

import com.grandle.pessoa.dtos.EnderecoDTO;
import com.sistema.contas.endereco.domain.entities.Endereco;

public class PessoaEnderecoConverters {
    public static EnderecoDTO toDto(Endereco entity) {
        if (entity == null) {
            return null;
        }

        // Convertendo Endereco para EnderecoDTO
        return new EnderecoDTO(
                Math.toIntExact(entity.getId()), // Converte Long para Integer
                entity.getLogradouro(),
                entity.getRua(),
                entity.getNumero(),
                entity.getComplemento(),
                entity.getBairro(),
                entity.getCep(),
                entity.getCidade(),
                entity.getEstado()
        );
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        // Implementação de conversão do DTO para a entidade
        return Endereco.builder()
                .logradouro(dto.getLogradouro())
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .build();
    }
}
