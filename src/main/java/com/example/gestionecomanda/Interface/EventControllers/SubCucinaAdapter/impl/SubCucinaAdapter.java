package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class SubCucinaAdapter {

    private CountDownLatch latch = new CountDownLatch(1);
    private final Logger logger = LoggerFactory.getLogger(SubCucinaAdapter.class);

    @KafkaListener(id = "cucina", topics = "${gestionecomanda.gestionecucina.topic}")
    public void receive(@Payload String message,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {
        logger.info("Received a message {}, from {} topic, " +
                "{} partition, and {} offset", message.toString(), topic, partition, offset);
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
