package com.example.gestionecomanda.Interface.testControllers.impl;

import com.example.gestionecomanda.Infrastructure.MessageBroker.service.CucinaPubAdapter;
import com.example.gestionecomanda.Infrastructure.MessageBroker.service.impl.CucinaPubService;
import com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.NotifyOrderEvent;
import com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.impl.SubClienteAdapter;
import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.NotifyPrepEvent;
import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl.SubCucinaAdapter;
import com.example.gestionecomanda.Interface.testControllers.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Log
public class TestServiceImpl implements TestService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final CucinaPubAdapter cucinaPubAdapter;
    private final NotifyOrderEvent notifyOrderEvent;
    private final NotifyPrepEvent notifyPrepEvent;
    private String lastMessageReceived;

    @Autowired
    public TestServiceImpl(final KafkaTemplate<String, String> kafkaTemplate,
                           final ObjectMapper objectMapper,
                           final CucinaPubService cucinaPubService,
                           final SubClienteAdapter subClienteAdapter,
                           final SubCucinaAdapter subCucinaAdapter){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.cucinaPubAdapter = cucinaPubService;
        this.notifyOrderEvent = subClienteAdapter;
        this.notifyPrepEvent = subCucinaAdapter;
    }

    /**
     * Invia sul topic SendOrderEvent un oggetto tramite message broker
     *
     * @param message oggetto da inviare
     * @throws JsonProcessingException
     */
    @Override
    public void sendMessageToTopicSendOrderEvent(String message) throws JsonProcessingException {
        cucinaPubAdapter.sendMessageToTopic(message);
    }

    /**
     * Invia sul topic specificato un oggetto tramite message broker
     *
     * @param message oggetto da inviare
     * @param topic topic su quale inviare il messaggio
     * @throws JsonProcessingException
     */
    @Override
    public void sendMessageToTopic(String message, String topic) throws JsonProcessingException {

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

    /**
     * listener di kafka, resta in ascolto sul topic specificato e
     * aggiorna lastMessageReceived appena riceve un nuovo messaggio
     *
     */
    @KafkaListener(id = "${spring.kafka.producer.group-id}", topics = "${spring.kafka.producer.topic}")
    private void receive(@Payload String message) {
        lastMessageReceived = message.toString();
    }

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic SendOrderEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    @Override
    public Optional<String> peekFromSendOrderEvent(){
        return Optional.ofNullable(lastMessageReceived);
    }

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic NotifyOrderEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    @Override
    public Optional<String> peekFromNotifyOrderEvent(){
        return Optional.ofNullable(notifyOrderEvent.getLastMessageReceived());
    }

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic NotifyPrepEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    @Override
    public Optional<String> peekFromNotifyPrepEvent(){
        return Optional.ofNullable(notifyPrepEvent.getLastMessageReceived());
    }

}