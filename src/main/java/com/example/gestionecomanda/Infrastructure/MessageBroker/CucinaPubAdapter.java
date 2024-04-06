package com.example.gestionecomanda.Infrastructure.MessageBroker;

import com.example.gestionecomanda.Infrastructure.MessageBroker.config.KafkaProducerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Log
public class CucinaPubAdapter {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProducerConfig kafkaProducerConfig;
    private final ObjectMapper objectMapper;

    @Autowired
    public CucinaPubAdapter (final KafkaTemplate<String, String> kafkaTemplate, final KafkaProducerConfig kafkaProducerConfig, final ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.objectMapper = objectMapper;
    }

    public void sendMessageToTopic(String message) throws JsonProcessingException {

        final String payload = objectMapper.writeValueAsString(message);

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaProducerConfig.getTopic(), payload);
        future.whenComplete((result,ex)->{
            if(ex == null){
                log.info("Sent Message=[" + payload + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            else{
                log.info("Unable to send message=[" + payload + "] due to : " + ex.getMessage());
            }
        });
    }
}
