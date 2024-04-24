package com.example.gestionecomanda.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Classe configurazione JPA utile per operazioni sul DataBase
 */
@EnableJpaRepositories(basePackages = "com.example.gestionecomanda.Domain.Repository")
public class JPAConfiguration {
}
