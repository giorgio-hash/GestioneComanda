package com.example.gestionecomanda.Infrastructure.MessageBroker.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CucinaPubAdapter {

    /**
     * Tramite Kafka invia l'ordine nel topic del message broker correlato con la cucina
     *
     * @param message oggeto da inviare
     * @throws JsonProcessingException eccezione sollevata nella serializzazione
     */
    public void sendMessageToTopic(String message) throws JsonProcessingException;

}
