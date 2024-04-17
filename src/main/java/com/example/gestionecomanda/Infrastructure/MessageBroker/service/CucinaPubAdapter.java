package com.example.gestionecomanda.Infrastructure.MessageBroker.service;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CucinaPubAdapter {

    /**
     * Tramite Kafka invia l'ordine nel topic del message broker correlato con la cucina
     *
     * @param ordineDTO DTO dell'entita' ordine da inviare
     * @throws JsonProcessingException eccezione sollevata nella serializzazione
     */
    void sendMessageToTopic(OrdineDTO ordineDTO) throws JsonProcessingException;

}
