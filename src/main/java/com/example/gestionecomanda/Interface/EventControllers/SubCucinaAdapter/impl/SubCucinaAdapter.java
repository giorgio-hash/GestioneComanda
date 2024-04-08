package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl;

import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.NotifyPrepEvent;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class SubCucinaAdapter implements NotifyPrepEvent {

    @KafkaListener(id = "cucina", topics = "cucina.ordini")
    public String listens(final String in) {
        log.info("Consumed: " + in + ", on topic: cucina.ordini");
        return in;
    }
}
