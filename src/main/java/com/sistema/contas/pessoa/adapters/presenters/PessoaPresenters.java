package com.sistema.contas.pessoa.adapters.presenters;

import com.grandle.pessoa.dtos.PessoaDTO;
import com.sistema.contas.pessoa.adapters.converters.PessoaEnderecoConverters;
import com.sistema.contas.pessoa.domain.entities.Pessoa;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaPresenters {

    // Método para transformar uma PessoaDTO em um formato de resposta (exemplo)
    public static PessoaDTO formatPessoaResponse(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        // Convertendo a entidade Pessoa para o DTO correspondente
        PessoaDTO pessoaDTO = new PessoaDTO(
                pessoa.getId() != null ? pessoa.getId().intValue() : null,
                pessoa.getNome(),
                pessoa.getAtivo(),
                PessoaEnderecoConverters.toDto(pessoa.getEndereco())  // Assumindo que EnderecoDTO é o esperado
        );
        return pessoaDTO;
    }

    // Método para transformar uma lista de pessoas em uma lista de DTOs
    public static List<PessoaDTO> formatListaPessoasResponse(List<Pessoa> pessoas) {
        if (pessoas == null) {
            return null;
        }

        return pessoas.stream()
                .map(PessoaPresenters::formatPessoaResponse)  // Usando o método de formatação individual
                .collect(Collectors.toList());
    }

    // Exemplo de formato personalizado (caso precise de algo mais específico)
    public static PessoaDTO formatPessoaCustomResponse(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        // Aqui, você pode personalizar a formatação conforme necessário
        // Exemplo simples de adicionar campos extras no DTO ou formatar a resposta de outra forma

        PessoaDTO pessoaDTO = formatPessoaResponse(pessoa);
        // Aqui você pode adicionar ou modificar o DTO antes de retorná-lo
        pessoaDTO.setNome(pessoaDTO.getNome().toUpperCase()); // Exemplo de modificação do nome
        return pessoaDTO;
    }
}
