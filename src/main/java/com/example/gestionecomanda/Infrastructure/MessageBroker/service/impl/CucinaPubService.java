package com.example.gestionecomanda.Infrastructure.MessageBroker.service.impl;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Infrastructure.MessageBroker.CucinaPubProducer;
import com.example.gestionecomanda.Infrastructure.MessageBroker.service.CucinaPubAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CucinaPubService implements CucinaPubAdapter {

    private final CucinaPubProducer cucinaPubProducer;

    @Autowired
    public CucinaPubService(CucinaPubProducer cucinaPubProducer) {
        this.cucinaPubProducer = cucinaPubProducer;
    }

    /**
     * Tramite Kafka invia l'ordine nel topic del message broker correlato con la cucina
     *
     * @param ordineDTO DTO dell'entita' ordine da inviare
     * @throws JsonProcessingException eccezione sollevata nella serializzazione
     */
    @Override
    public void sendMessageToTopic(OrdineDTO ordineDTO) throws JsonProcessingException {
        cucinaPubProducer.send(ordineDTO);
    }

}
