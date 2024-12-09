package com.sistema.contas.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.beans.factory.annotation.Value;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtDecoderConfig {

//    private static final String SECRET_KEY = "testesenhasecreta"; // Substitua pela sua chave secreta segura.
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
//        return NimbusJwtDecoder.withSecretKey(secretKey).build();
//    }

    // O Spring irá injetar o valor da chave secreta definida no application.yml
    @Value("${spring.jwt.secret.key}")
    private String secretKey;

    @Bean
    public JwtDecoder jwtDecoder() {
        // Convertemos a chave secreta (String) para bytes e criamos o SecretKey
        SecretKey secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }
}
