package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl;

import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.NotifyPrepEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class SubCucinaAdapter implements NotifyPrepEvent {

    /**
     * variabile thread safe che serve per fini di test per verificare che il listener abbia ricevuto un messaggio
     */
    private CountDownLatch latch = new CountDownLatch(1);
    private final Logger logger = LoggerFactory.getLogger(SubCucinaAdapter.class);
    private String lastMessageReceived;

    /**
     * Riceve un messaggio tramite Kafka dal servizio gestioneCucina in merito all'avvenuta ordinazione
     * da parte di un cliente
     *
     * @param message il corpo del messaggio vero e proprio
     * @param topic topic del message broker sul quale si riceve il messaggio
     * @param partition numero di partizione sul quale si riceve il messaggio
     * @param offset numero di offset che presenta il messaggio ricevuto
     */
    @KafkaListener(id = "${spring.kafka.consumer.gestioneCucina.group-id}", topics = "${spring.kafka.consumer.gestioneCucina.topic}")
    public void receive(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {
        logger.info("Received a message {}, from {} topic, " +
                "{} partition, and {} offset", message.toString(), topic, partition, offset);
        lastMessageReceived = message.toString();
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
    public String getLastMessageReceived() {
        return lastMessageReceived;
    }
}
