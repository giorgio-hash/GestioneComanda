package com.example.gestionecomanda.Infrastructure.MessageBroker.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CucinaPubAdapter {

    public void sendMessageToTopic(String message) throws JsonProcessingException;

}
