package com.sistema.contas.endereco.infrastructure.services;

import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.application.ports.service.IEnderecoService;
import com.sistema.contas.endereco.domain.usecases.EnderecoUseCase;
import com.sistema.contas.endereco.domain.validators.EnderecoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService implements IEnderecoService {

    private final EnderecoUseCase enderecoUseCase; // Usando o EnderecoUseCase

    @Override
    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        // Usa o caso de uso para salvar o endereço, já retornando o DTO correspondente
        EnderecoValidator.validar(enderecoDTO);
        return enderecoUseCase.salvarEndereco(enderecoDTO);
    }

    @Override
    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        // Usa o caso de uso para atualizar o endereço
        return enderecoUseCase.atualizarEndereco(id, enderecoDTO);
    }

    @Override
    public EnderecoDTO obterEndereco(Long id) {
        // Usa o caso de uso para obter o endereço pelo ID
        return enderecoUseCase.obterEndereco(id);
    }

    @Override
    public List<EnderecoDTO> listarEnderecos(String filtro) {
        // Usa o caso de uso para listar os endereços
        return enderecoUseCase.listarEnderecos(filtro);
    }

    @Override
    public void removerEndereco(Long id) {
        // Usa o caso de uso para remover o endereço pelo ID
        enderecoUseCase.removerEndereco(id);
    }
}
