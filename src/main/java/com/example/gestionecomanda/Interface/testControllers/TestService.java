package com.example.gestionecomanda.Interface.testControllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Optional;

public interface TestService {

    /**
     * Invia sul topic SendOrderEvent un oggetto tramite message broker
     *
     * @param message oggetto da inviare
     * @throws JsonProcessingException
     */
    void sendMessageToTopicSendOrderEvent(String message) throws JsonProcessingException;

    /**
     * Invia sul topic specificato un oggetto tramite message broker
     *
     * @param message oggetto da inviare
     * @param topic topic su quale inviare il messaggio
     * @throws JsonProcessingException
     */
    void sendMessageToTopic(String message, String topic) throws JsonProcessingException;

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic SendOrderEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    Optional<String> peekFromSendOrderEvent();

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic NotifyOrderEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    Optional<String> peekFromNotifyOrderEvent();

    /**
     * Restituisce l'ultimo messaggio letto dal consumer sul topic NotifyPrepEvent
     *
     * @return l'ultimo messaggio letto dal consumer se non è null, altrimenti un optional il cui valore è null
     */
    Optional<String> peekFromNotifyPrepEvent();

}
