package com.example.gestionecomanda.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.gestionecomanda.Domain.Repository")
public class JPAConfiguration {
}
