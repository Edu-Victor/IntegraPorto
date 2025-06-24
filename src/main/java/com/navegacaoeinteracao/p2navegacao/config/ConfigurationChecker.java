package com.navegacaoeinteracao.p2navegacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationChecker implements CommandLineRunner {

    @Value("${spring.datasource.hikari.auto-commit}")
    private boolean autoCommitValue;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("====================================================================");
        System.out.println("VERIFICANDO CONFIGURAÇÃO: O valor de 'auto-commit' é: " + autoCommitValue);
        System.out.println("====================================================================");
    }
}
