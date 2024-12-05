package com.sistema.contas.endereco.adapters.presenters;

import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.domain.entities.Endereco;
import com.sistema.contas.endereco.adapters.converters.EnderecoConverter;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoPresenter {

    // Método para transformar um único Endereco para EnderecoDTO usando o EnderecoConverter
    public static EnderecoDTO toDto(Endereco endereco) {
        // Usando o EnderecoConverter para fazer a conversão
        return EnderecoConverter.toDto(endereco);
    }

    // Método para transformar uma lista de Enderecos para uma lista de EnderecoDTO
    public static List<EnderecoDTO> toDtoList(List<Endereco> enderecos) {
        // Usando o EnderecoConverter para fazer a conversão em massa
        return enderecos.stream()
                .map(EnderecoConverter::toDto) // Aqui, estamos usando o método toDto do EnderecoConverter
                .collect(Collectors.toList());
    }
}
