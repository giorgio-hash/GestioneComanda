package com.example.gestionecomanda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        final ObjectMapper objectMapper = new ObjectMapper();
        // configurazione di timestamp
        objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        return objectMapper;
    }

}
