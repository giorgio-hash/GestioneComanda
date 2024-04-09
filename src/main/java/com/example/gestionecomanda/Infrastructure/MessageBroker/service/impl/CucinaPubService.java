package com.example.gestionecomanda.Infrastructure.MessageBroker.service.impl;

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

    @Override
    public void sendMessageToTopic(String message) throws JsonProcessingException {
        cucinaPubProducer.send(message);
    }

}
