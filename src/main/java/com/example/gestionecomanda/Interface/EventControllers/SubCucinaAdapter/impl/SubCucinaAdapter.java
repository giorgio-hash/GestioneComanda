package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl;

import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.ports.CucinaPort;
import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.NotifyPrepEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class SubCucinaAdapter implements NotifyPrepEvent {

    private ObjectMapper objectMapper;
    private final CucinaPort cucinaPort;

    /**
     * variabile thread safe che serve per fini di test per verificare che il listener abbia ricevuto un messaggio
     */
    private CountDownLatch latch = new CountDownLatch(1);
    private final Logger logger = LoggerFactory.getLogger(SubCucinaAdapter.class);
    private NotificaPrepOrdineDTO lastMessageReceived;

    @Autowired
    public SubCucinaAdapter(CucinaPort cucinaPort, ObjectMapper objectMapper) {
        this.cucinaPort = cucinaPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public void receive(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {
        logger.info("Received a message {}, from {} topic, " +
                "{} partition, and {} offset", message.toString(), topic, partition, offset);
        NotificaPrepOrdineDTO notificaPrepOrdineDTO = objectMapper.readValue(message, NotificaPrepOrdineDTO.class);
        cucinaPort.setPreparationStatus(notificaPrepOrdineDTO);
        lastMessageReceived = notificaPrepOrdineDTO;
        latch.countDown();
    }

    /**
     * resetta il valore del latch
     */
    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    /**
     * restituisce il latch
     *
     * @return latch: variabile thread safe che serve per fini di test per verificare
     * che il listener abbia ricevuto un messaggio
     */
    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    public NotificaPrepOrdineDTO getLastMessageReceived() {
        return lastMessageReceived;
    }
}
