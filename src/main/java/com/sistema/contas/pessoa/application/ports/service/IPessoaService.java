package com.sistema.contas.pessoa.application.ports.service;

import com.sistema.contas.pessoa.domain.entities.Pessoa;
import org.springframework.data.domain.Page;

public interface IPessoaService {

    /**
     * Lista pessoas com paginação e ordenação.
     *
     * @param filtro    Filtro para buscar pelo nome.
     * @param ativo
     * @param page      Número da página.
     * @param size      Tamanho da página.
     * @param sortBy    Campo para ordenação.
     * @param direction Direção da ordenação (ascendente ou descendente).
     * @return Página de pessoas.
     */
    Page<Pessoa> listarPessoas(String filtro, Boolean ativo, int page, int size, String sortBy, String direction);

    /**
     * Obtém uma pessoa por ID.
     *
     * @param id ID da pessoa.
     * @return Pessoa encontrada ou null se não existir.
     */
    Pessoa obterPessoaPorId(Long id);

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoa Pessoa a ser criada.
     * @return Pessoa criada.
     */
    Pessoa criarPessoa(Pessoa pessoa);

    /**
     * Atualiza uma pessoa existente.
     *
     * @param id ID da pessoa a ser atualizada.
     * @param pessoa Dados atualizados da pessoa.
     * @return Pessoa atualizada ou null se não existir.
     */
    Pessoa atualizarPessoa(Long id, Pessoa pessoa);

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param id ID da pessoa a ser removida.
     */
    void removerPessoa(Long id);
}
