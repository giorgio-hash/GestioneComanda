package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class SubCucinaAdapter implements NotifyPrepEvent{

    Logger log = LoggerFactory.getLogger(SubCucinaAdapter.class);

    @KafkaListener(topics = "cucina-demo-2",groupId = "gestionecomanda")
    @Override
    public void send(String message) {
        log.info("consumer consume the events {} ", message);
    }
}
