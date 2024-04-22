package com.example.gestionecomanda.Domain.ports;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessagePort<T> {
    /**
     * Invia l'oggetto passato come parametro sul topic del message broker
     *
     * @param t oggetto da inviare
     * @throws JsonProcessingException eccezione sollevata nella serializzazione
     */
    void send(T t) throws JsonProcessingException;
}
