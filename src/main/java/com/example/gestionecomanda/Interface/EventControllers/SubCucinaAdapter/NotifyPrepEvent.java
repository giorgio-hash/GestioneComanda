package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter;

import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotifyPrepEvent {

    /**
     * Riceve un messaggio tramite Kafka dal servizio gestioneCucina in merito all'avvenuta ordinazione
     * da parte di un cliente
     *
     * @param message il corpo del messaggio vero e proprio
     * @param topic topic del message broker sul quale si riceve il messaggio
     * @param partition numero di partizione sul quale si riceve il messaggio
     * @param offset numero di offset che presenta il messaggio ricevuto
     */
    @KafkaListener(id = "${spring.kafka.consumer.gestioneCucina.group-id}", topics = "${spring.kafka.consumer.gestioneCucina.topic}")
    void receive(String message, String topic, Integer partition, Long offset) throws JsonProcessingException;

    /**
     * Restituisce l'ultimo messaggio letto dal listener
     *
     * @return l'ultimo messaggio letto dal listener
     */
    NotificaPrepOrdineDTO getLastMessageReceived();

}
