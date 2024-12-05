package com.sistema.contas.pessoa.domain.validators;

import com.sistema.contas.pessoa.domain.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaValidator {

    /**
     * Valida uma instância da entidade Pessoa.
     *
     * @param pessoa A instância de Pessoa a ser validada.
     * @return Uma lista de mensagens de erro; estará vazia se não houver erros.
     */
    public static List<String> validar(Pessoa pessoa) {
        List<String> erros = new ArrayList<>();

        if (pessoa == null) {
            erros.add("Pessoa não pode ser nula.");
            return erros;
        }

        // Valida o campo 'nome'
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            erros.add("O nome da pessoa não pode estar vazio.");
        }

        // Valida o campo 'ativo'
        if (pessoa.getAtivo() == null) {
            erros.add("O campo 'ativo' deve ser preenchido (true ou false).");
        }

        // Valida o campo 'endereco'
        if (pessoa.getEndereco() == null) {
            erros.add("O endereço da pessoa não pode ser nulo.");
        }

        return erros;
    }
}
