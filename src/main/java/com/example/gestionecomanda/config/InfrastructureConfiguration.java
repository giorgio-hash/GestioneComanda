package com.example.gestionecomanda.config;

import com.example.gestionecomanda.Domain.Repository.ClienteRepository;
import com.example.gestionecomanda.Infrastructure.Repository.JPADBAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    JPADBAdapter jpadbAdapter(ClienteRepository clienteRepository){
        return new JPADBAdapter(clienteRepository);
    }

}
