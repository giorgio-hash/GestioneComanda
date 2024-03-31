package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("cliente-demo",1,(short)1);
    };

}
