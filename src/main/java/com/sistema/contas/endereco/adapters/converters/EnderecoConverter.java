package com.sistema.contas.endereco.adapters.converters;


import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.domain.entities.Endereco;
import jakarta.validation.Valid;

public class EnderecoConverter {
    public static Endereco toEntity(com.grandle.pessoa.dtos.@Valid EnderecoDTO dto) {
        // Não definimos o ID, pois ele será gerado automaticamente
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

    public static EnderecoDTO toDto(Endereco entity) {
        return new EnderecoDTO(
                Math.toIntExact(entity.getId()), // Pode ser nulo antes de persistir
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
}
