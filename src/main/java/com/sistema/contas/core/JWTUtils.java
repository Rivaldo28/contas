package com.sistema.contas.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JWTUtils {

    /**
     * Extrai o e-mail do JWT.
     *
     * @param jwt O JWT.
     * @return O e-mail contido no JWT.
     */
    public static String obterEmail(Jwt jwt) {
        // Obtendo o e-mail da claim "email" (modifique a chave conforme necessário)
        return jwt.getClaimAsString("email");
    }


    public static String obterPerfil(Jwt jwt) {
        List<Map<String, Object>> corporativoList = jwt.getClaim("corporativo");

        if (corporativoList != null && !corporativoList.isEmpty()) {
            Map<String, Object> corporativoMap = corporativoList.get(0);  // Ajustado para pegar o primeiro item
            return (String) corporativoMap.get("dsc_perfil");
        }

        return null;
    }
}
