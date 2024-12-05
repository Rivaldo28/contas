package com.sistema.contas.categoria.adapters.controllers;

import com.grandle.categoria.controllers.CategoriaApi;
import com.grandle.categoria.dtos.CategoriaDTO;
import com.grandle.categoria.dtos.CategoriaInput;
import com.sistema.contas.categoria.applications.ports.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriaController implements CategoriaApi {

    private final ICategoriaService categoriaService;

    @Override
    public ResponseEntity<CategoriaDTO> atualizarCategoria(Integer id, CategoriaInput categoriaInput) throws Exception {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaInput);
        if (categoriaAtualizada != null) {
            return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Caso a categoria não seja encontrada
        }
    }

    @Override
    public ResponseEntity<CategoriaDTO> criarCategoria(CategoriaInput categoriaInput) throws Exception {
        CategoriaDTO novaCategoria = categoriaService.criarCategoria(categoriaInput);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() throws Exception {
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoriaDTO> obterCategoria(Integer id) throws Exception {
        CategoriaDTO categoria = categoriaService.obterCategoriaPorId(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Caso a categoria não seja encontrada
        }
    }

    @Override
    public ResponseEntity<Void> removerCategoria(Integer id) throws Exception {
        try {
            categoriaService.removerCategoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Remoção bem-sucedida
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Caso a categoria não seja encontrada
        }
    }
}
