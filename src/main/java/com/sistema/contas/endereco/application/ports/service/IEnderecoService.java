package com.sistema.contas.endereco.application.ports.service;



import com.grandle.endereco.dtos.EnderecoDTO;

import java.util.List;

public interface IEnderecoService {

    EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO);

    EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO);

    EnderecoDTO obterEndereco(Long id);

    List<EnderecoDTO> listarEnderecos(String filtro);

    void removerEndereco(Long id);
}
