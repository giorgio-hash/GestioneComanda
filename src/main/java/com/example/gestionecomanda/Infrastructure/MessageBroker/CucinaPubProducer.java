package com.example.gestionecomanda.Infrastructure.MessageBroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Log
public class CucinaPubProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    /**
     * topic sul quale è in ascolto la cucina
     */
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    @Autowired
    public CucinaPubProducer(final KafkaTemplate<String, String> kafkaTemplate, final ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * serializza e invia l'oggetto passato come parametro nel topic verso la cucina
     *
     * @param message oggetto da inviare
     * @throws JsonProcessingException eccezione sollevata nella serializzazione
     */
    public void send(String message) throws JsonProcessingException {

        final String payload = objectMapper.writeValueAsString(message);

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, payload);
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
