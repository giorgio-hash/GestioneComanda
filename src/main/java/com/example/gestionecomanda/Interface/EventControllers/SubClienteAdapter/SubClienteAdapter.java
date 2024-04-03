package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubClienteAdapter implements NotifyOrderEvent{

    Logger log = LoggerFactory.getLogger(SubClienteAdapter.class);
    @KafkaListener(topics = "cliente-demo",groupId = "gestionecomanda")
    @Override
    public void send(String message) {
        log.info("consumer consume the events {} ", message);
    }
}
