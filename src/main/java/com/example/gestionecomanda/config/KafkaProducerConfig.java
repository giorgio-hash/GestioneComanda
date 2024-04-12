package com.example.gestionecomanda.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gestionecomanda.gestionecucina")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaProducerConfig {

    private String topic;

}
