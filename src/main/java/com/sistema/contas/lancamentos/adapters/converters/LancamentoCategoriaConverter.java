package com.sistema.contas.lancamentos.adapters.converters;


import com.grandle.lancamento.dtos.CategoriaDTO;
import com.sistema.contas.categoria.domain.entities.Categoria;
import org.springframework.stereotype.Component;

@Component
public class LancamentoCategoriaConverter {

    // Converte a entidade Categoria para o DTO CategoriaDTO
    public CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(Math.toIntExact(categoria.getId()));  // Supondo que Categoria tenha um campo id
        categoriaDTO.setNome(categoria.getNome());  // Supondo que Categoria tenha um campo nome
        // Adicione mais campos conforme necessário

        return categoriaDTO;
    }

    // Converte o DTO CategoriaDTO para a entidade Categoria
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        if (categoriaDTO == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(Long.valueOf(categoriaDTO.getId()));  // Supondo que Categoria tenha um campo id
        categoria.setNome(categoriaDTO.getNome());  // Supondo que Categoria tenha um campo nome
        // Adicione mais campos conforme necessário

        return categoria;
    }
}
