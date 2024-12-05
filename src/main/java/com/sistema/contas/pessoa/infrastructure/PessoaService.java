package com.sistema.contas.pessoa.infrastructure;

import com.sistema.contas.pessoa.application.ports.repository.PessoaRepository;
import com.sistema.contas.pessoa.application.ports.service.IPessoaService;
import com.sistema.contas.pessoa.domain.entities.Pessoa;
import com.sistema.contas.pessoa.domain.usecases.PessoaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements IPessoaService {

    private final PessoaUseCase pessoaUseCase;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaUseCase = new PessoaUseCase(pessoaRepository);
    }

    /**
     * Lista pessoas com paginação e ordenação.
     *
     * @param filtro Filtro para buscar pelo nome.
     * @param page Número da página.
     * @param size Tamanho da página.
     * @param sortBy Campo para ordenação.
     * @param direction Direção da ordenação (ascendente ou descendente).
     * @return Página de pessoas.
     */
    @Override
    public Page<Pessoa> listarPessoas(String filtro, Boolean ativo, int page, int size, String sortBy, String direction) {
        // Adicionando filtro para o parâmetro "ativo" (se necessário)
        String filtroAplicado = (filtro == null || filtro.isEmpty()) ? "%" : "%" + filtro + "%";

        // Delegar a lógica para o caso de uso, incluindo o filtro "ativo"
        return pessoaUseCase.listarPessoas(filtroAplicado, ativo, page, size, sortBy, direction);
    }


    /**
     * Busca uma pessoa pelo ID.
     *
     * @param id ID da pessoa.
     * @return Pessoa encontrada ou null se não existir.
     */
    public Pessoa obterPessoaPorId(Long id) {
        return pessoaUseCase.obterPessoaPorId(id);
    }

    /**
     * Cria uma nova pessoa.
     *
     * @param pessoa Objeto Pessoa a ser criado.
     * @return Pessoa criada.
     */
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaUseCase.criarPessoa(pessoa);
    }

    /**
     * Atualiza uma pessoa existente.
     *
     * @param id ID da pessoa a ser atualizada.
     * @param pessoa Dados atualizados da pessoa.
     * @return Pessoa atualizada ou null se não existir.
     */
    public Pessoa atualizarPessoa(Long id, Pessoa pessoa) {
        return pessoaUseCase.atualizarPessoa(id, pessoa);
    }

    /**
     * Remove uma pessoa por ID.
     *
     * @param id ID da pessoa a ser removida.
     */
    public void removerPessoa(Long id) {
        pessoaUseCase.removerPessoa(id);
    }
}
