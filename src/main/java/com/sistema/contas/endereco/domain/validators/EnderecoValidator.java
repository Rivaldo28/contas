package com.sistema.contas.endereco.domain.validators;

import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.domain.entities.Endereco;

public class EnderecoValidator {
    public static void validar(EnderecoDTO endereco) {
        if (endereco.getCep() == null || !endereco.getCep().matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido!");
        }
    }

}
