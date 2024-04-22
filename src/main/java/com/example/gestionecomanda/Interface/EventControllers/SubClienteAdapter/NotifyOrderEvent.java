package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter;

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
    void receive(String message, String topic, Integer partition, Long offset);

    /**
     * Restituisce l'ultimo messaggio letto dal listener
     *
     * @return l'ultimo messaggio letto dal listener
     */
    String getLastMessageReceived();

}
