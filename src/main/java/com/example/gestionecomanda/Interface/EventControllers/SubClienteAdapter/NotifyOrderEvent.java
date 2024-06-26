package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter;

import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.annotation.KafkaListener;

public interface NotifyOrderEvent {

    /**
     * Riceve un messaggio tramite Kafka dal servizio gestioneCliente in merito all'avvenuta ordinazione
     * da parte di un cliente
     *
     * @param message il corpo del messaggio vero e proprio
     * @param topic topic del message broker sul quale si riceve il messaggio
     * @param partition numero di partizione sul quale si riceve il messaggio
     * @param offset numero di offset che presenta il messaggio ricevuto
     */
    @KafkaListener(id = "${spring.kafka.consumer.gestioneCliente.group-id}", topics = "${spring.kafka.consumer.gestioneCliente.topic}")
    void receive(String message, String topic, Integer partition, Long offset) throws JsonProcessingException;

    /**
     * Restituisce l'ultima notifica letta dal listener
     *
     * @return oggetto notifica letto dal listener
     */
    NotificaOrdineDTO getLastMessageReceived();

}
