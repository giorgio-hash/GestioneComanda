package com.example.gestionecomanda.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaConfig {

    /**
     * When using Spring Boot, a KafkaAdmin bean is automatically registered
     * a KafkaAdmin bean add automatically topics to the broker.
     * add a NewTopic @Bean for each topic to the application context.
     * https://docs.spring.io/spring-kafka/reference/kafka/configuring-topics.html
     */

    @Bean
    public NewTopic topic(final KafkaProducerConfig kafkaProducerConfig) {
        return TopicBuilder.name(kafkaProducerConfig.getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

}
