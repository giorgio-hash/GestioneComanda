package com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.impl;

import com.example.gestionecomanda.Interface.EventControllers.SubClienteAdapter.NotifyOrderEvent;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
@Log
public class SubClienteAdapter implements NotifyOrderEvent {

    //@KafkaListener(topics = "")
    public String listens(final String in) {
        log.info("Consumed: " + in + ", on topic: ");
        return in;
    }
}