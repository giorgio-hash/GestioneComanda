package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.impl;

import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.ports.ClientePort;
import com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.NotifyOrderEvent;
import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl.SubCucinaAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Log
public class SubClienteAdapter implements NotifyOrderEvent {

    private ObjectMapper objectMapper;
    private ClientePort clientePort;

    /**
     * variabile thread safe che serve per fini di test per verificare che il listener abbia ricevuto un messaggio
     */
    private CountDownLatch latch = new CountDownLatch(1);
    private final Logger logger = LoggerFactory.getLogger(SubCucinaAdapter.class);
    private NotificaOrdineDTO lastMessageReceived;

    @Autowired
    public SubClienteAdapter(final ClientePort clientePort, final ObjectMapper objectMapper) {
        this.clientePort = clientePort;
        this.objectMapper = objectMapper;
    }

    @Override
    public void receive(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {
        logger.info("Received a message {}, from {} topic, " +
                "{} partition, and {} offset", message.toString(), topic, partition, offset);
        NotificaOrdineDTO notificaOrdineDTO = objectMapper.readValue(message, NotificaOrdineDTO.class);
        clientePort.notifyOrder(notificaOrdineDTO);
        lastMessageReceived = notificaOrdineDTO;
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

    /**
     * Restituisce l'ultimo messaggio letto dal listener
     *
     * @return l'ultimo messaggio letto dal listener
     */
    @Override
    public NotificaOrdineDTO getLastMessageReceived() {
        return lastMessageReceived;
    }
}
