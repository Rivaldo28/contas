package com.sistema.contas.pessoa.domain.usecases;

import com.sistema.contas.pessoa.application.ports.repository.PessoaRepository;
import com.sistema.contas.pessoa.domain.entities.Pessoa;
import com.sistema.contas.pessoa.domain.validators.PessoaValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PessoaUseCase {

    private final PessoaRepository pessoaRepository;

    // Construtor para injetar o repositório
    public PessoaUseCase(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Lista pessoas com paginação e filtro pelo nome.
     *
     * @param nome Nome parcial para filtro com LIKE.
     * @param page Número da página.
     * @param size Tamanho da página.
     * @param sortBy Campo para ordenação.
     * @param direction Direção da ordenação (ascendente ou descendente).
     * @return Página de pessoas.
     */
    public Page<Pessoa> listarPessoas(String filtro, Boolean ativo, int page, int size, String sortBy, String direction) {
        // Lógica para listar pessoas, incluindo o filtro por "ativo"
        // Exemplo: Usar o repositório para aplicar os filtros
        return pessoaRepository.findAllByFiltroAndAtivo(filtro, ativo, PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)));
    }

    /**
     * Busca uma pessoa por ID.
     *
     * @param id ID da pessoa.
     * @return Instância de Pessoa encontrada ou null se não existir.
     */
    public Pessoa obterPessoaPorId(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoa Objeto Pessoa a ser criado.
     * @return Pessoa criada.
     */
    public Pessoa criarPessoa(Pessoa pessoa) {
        List<String> erros = PessoaValidator.validar(pessoa);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro ao criar Pessoa: " + String.join(", ", erros));
        }

        return pessoaRepository.save(pessoa);
    }

    /**
     * Atualiza uma pessoa existente.
     *
     * @param id ID da pessoa a ser atualizada.
     * @param pessoa Dados atualizados da pessoa.
     * @return Pessoa atualizada ou null se não existir.
     */
    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
        Pessoa existente = pessoaRepository.findById(id).orElse(null);

        if (existente == null) {
            throw new IllegalArgumentException("Pessoa com ID " + id + " não encontrada.");
        }

        // Atualiza os campos da pessoa existente
        existente.setNome(pessoa.getNome());
        existente.setAtivo(pessoa.getAtivo());
        existente.setEndereco(pessoa.getEndereco());

        List<String> erros = PessoaValidator.validar(existente);

        if (!erros.isEmpty()) {
            throw new IllegalArgumentException("Erro ao atualizar Pessoa: " + String.join(", ", erros));
        }

        return pessoaRepository.save(existente);
    }

    /**
     * Remove uma pessoa por ID.
     *
     * @param id ID da pessoa a ser removida.
     */
    public void removerPessoa(Long id) {
        Pessoa existente = pessoaRepository.findById(id).orElse(null);

        if (existente == null) {
            throw new IllegalArgumentException("Pessoa com ID " + id + " não encontrada.");
        }

        pessoaRepository.deleteById(id);
    }
}
