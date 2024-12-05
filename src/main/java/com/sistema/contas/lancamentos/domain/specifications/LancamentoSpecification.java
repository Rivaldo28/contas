package com.sistema.contas.lancamentos.domain.specifications;

import com.sistema.contas.lancamentos.domain.entities.Lancamento;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import jakarta.persistence.criteria.Predicate;

public class LancamentoSpecification {
    public static Specification<Lancamento> filterByParams(String descricao, LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            // Adiciona condições de filtro aqui, por exemplo:
            Predicate p = criteriaBuilder.conjunction();

            if (descricao != null && !descricao.isEmpty()) {
                p = criteriaBuilder.and(p, criteriaBuilder.like(root.get("descricao"), "%" + descricao + "%"));
            }
            if (dataInicio != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.greaterThanOrEqualTo(root.get("dataPagamento"), dataInicio));
            }
            if (dataFim != null) {
                p = criteriaBuilder.and(p, criteriaBuilder.lessThanOrEqualTo(root.get("dataPagamento"), dataFim));
            }
//            if (tipo != null && !tipo.isEmpty()) {
//                p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("tipo"), tipo));
//            }

            return p;
        };
    }
}
