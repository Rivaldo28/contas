package com.sistema.contas.lancamentos.adapters.converters;

import com.grandle.lancamento.dtos.EnderecoDTO;
import com.sistema.contas.endereco.domain.entities.Endereco;
import org.springframework.stereotype.Component;

@Component
public class LancamentoEnderecoConverter {

    public static Endereco toEntity(EnderecoDTO dto) {
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

