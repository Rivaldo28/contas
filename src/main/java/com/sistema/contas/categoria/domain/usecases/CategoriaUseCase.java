package com.sistema.contas.categoria.domain.usecases;

import com.sistema.contas.categoria.applications.ports.repository.CategoriaRepository;
import com.sistema.contas.categoria.domain.entities.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoriaUseCase {

    private final CategoriaRepository categoriaRepository;


    /**
     * Lista todas as categorias.
     *
     * @return Lista de categorias.
     */
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * Busca uma categoria pelo ID.
     *
     * @param id ID da categoria.
     * @return Categoria encontrada ou null se não existir.
     */
    public Optional<Categoria> obterCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    /**
     * Cria uma nova categoria.
     *
     * @param categoria Categoria a ser criada.
     * @return Categoria criada.
     */
    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /**
     * Atualiza uma categoria existente.
     *
     * @param id       ID da categoria a ser atualizada.
     * @param categoria Categoria com os novos dados.
     * @return Categoria atualizada ou null se não existir.
     */
    public Optional<Categoria> atualizarCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id); // Garantir que o ID seja mantido durante a atualização
            return Optional.of(categoriaRepository.save(categoria));
        }
        return Optional.empty();
    }

    /**
     * Remove uma categoria pelo ID.
     *
     * @param id ID da categoria a ser removida.
     */
    public void removerCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
