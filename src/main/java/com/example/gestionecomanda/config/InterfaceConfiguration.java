package com.example.gestionecomanda.config;

import com.example.gestionecomanda.Domain.DataPort;
import com.example.gestionecomanda.Domain.GestionePrioritaOrdini;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceConfiguration {

    @Bean
    GestionePrioritaOrdini gestionePrioritaOrdini(@Qualifier("jpadbAdapter") DataPort dataPort){
        return new GestionePrioritaOrdini(dataPort);
    }

}
