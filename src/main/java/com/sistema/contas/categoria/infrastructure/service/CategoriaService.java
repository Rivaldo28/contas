package com.sistema.contas.categoria.infrastructure.service;

import com.grandle.categoria.dtos.CategoriaDTO;
import com.sistema.contas.categoria.applications.ports.service.ICategoriaService;
import com.sistema.contas.categoria.domain.entities.Categoria;
import com.sistema.contas.categoria.domain.usecases.CategoriaUseCase;
import com.grandle.categoria.dtos.CategoriaInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final CategoriaUseCase categoriaUseCase;

    /**
     * Atualiza uma categoria com os dados fornecidos.
     *
     * @param id ID da categoria a ser atualizada.
     * @param categoriaInput Dados da categoria para atualização.
     * @return CategoriaDTO atualizada.
     * @throws Exception Caso a categoria não seja encontrada.
     */
    @Override
    public CategoriaDTO atualizarCategoria(Integer id, CategoriaInput categoriaInput) throws Exception {
        Optional<Categoria> categoriaOpt = categoriaUseCase.obterCategoriaPorId(Long.valueOf(id));
        if (categoriaOpt.isPresent()) {
            Categoria categoriaEntity = categoriaOpt.get();
            categoriaEntity.setNome(categoriaInput.getNome()); // Atualiza os campos da categoria
            categoriaUseCase.atualizarCategoria(Long.valueOf(id), categoriaEntity); // Atualiza a categoria
            return toDto(categoriaEntity); // Retorna o DTO
        } else {
            throw new Exception("Categoria não encontrada.");
        }
    }

    /**
     * Cria uma nova categoria com os dados fornecidos.
     *
     * @param categoriaInput Dados da nova categoria.
     * @return CategoriaDTO criada.
     * @throws Exception Caso ocorra algum erro durante a criação.
     */
    @Override
    public CategoriaDTO criarCategoria(CategoriaInput categoriaInput) throws Exception {
        Categoria categoriaEntity = new Categoria();
        categoriaEntity.setNome(categoriaInput.getNome()); // Define o nome da categoria
        categoriaEntity = categoriaUseCase.criarCategoria(categoriaEntity); // Cria a nova categoria
        return toDto(categoriaEntity); // Retorna o DTO
    }

    /**
     * Lista todas as categorias.
     *
     * @return Lista de categorias DTO.
     * @throws Exception Caso ocorra algum erro ao listar as categorias.
     */
    @Override
    public List<CategoriaDTO> listarCategorias() throws Exception {
        List<Categoria> categorias = categoriaUseCase.listarCategorias();
        return categorias.stream()
                .map(this::toDto) // Converte as entidades para DTOs
                .toList();
    }

    /**
     * Obtém uma categoria pelo ID.
     *
     * @param id ID da categoria.
     * @return CategoriaDTO encontrada.
     * @throws Exception Caso a categoria não seja encontrada.
     */
    @Override
    public CategoriaDTO obterCategoriaPorId(Integer id) throws Exception {
        Optional<Categoria> categoriaOpt = categoriaUseCase.obterCategoriaPorId(Long.valueOf(id));
        if (categoriaOpt.isPresent()) {
            return toDto(categoriaOpt.get()); // Converte a entidade para DTO
        } else {
            throw new Exception("Categoria não encontrada.");
        }
    }

    /**
     * Remove uma categoria pelo ID.
     *
     * @param id ID da categoria a ser removida.
     * @throws Exception Caso a categoria não seja encontrada.
     */
    @Override
    public void removerCategoria(Integer id) throws Exception {
        Optional<Categoria> categoriaOpt = categoriaUseCase.obterCategoriaPorId(Long.valueOf(id));
        if (categoriaOpt.isPresent()) {
            categoriaUseCase.removerCategoria(Long.valueOf(id)); // Remove a categoria
        } else {
            throw new Exception("Categoria não encontrada.");
        }
    }

    /**
     * Método auxiliar para converter uma entidade Categoria para o DTO CategoriaDTO.
     *
     * @param categoria A entidade Categoria.
     * @return O DTO CategoriaDTO.
     */
    private CategoriaDTO toDto(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(Math.toIntExact(categoria.getId())); // Atribui o ID da categoria
        dto.setNome(categoria.getNome()); // Atribui o nome da categoria
        return dto;
    }
}
