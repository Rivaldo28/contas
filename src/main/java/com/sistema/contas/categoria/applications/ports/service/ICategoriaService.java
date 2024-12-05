package com.sistema.contas.categoria.applications.ports.service;

import com.grandle.categoria.dtos.CategoriaDTO;
import com.grandle.categoria.dtos.CategoriaInput;

import java.util.List;

public interface ICategoriaService {

    CategoriaDTO atualizarCategoria(Integer id, CategoriaInput categoriaInput) throws Exception;

    CategoriaDTO criarCategoria(CategoriaInput categoriaInput) throws Exception;

    List<CategoriaDTO> listarCategorias() throws Exception;

    CategoriaDTO obterCategoriaPorId(Integer id) throws Exception;

    void removerCategoria(Integer id) throws Exception;
}
