package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.ports.CucinaPort;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class GestioneHeap implements CucinaPort {

    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;

    @Override
    public void setPreparationStatus(NotificaPrepOrdineDTO notificaPrepOrdineDTO) {
        log.info("Received a notify from "
                + topic_notifyPrepEvent
                + " : { id: " + notificaPrepOrdineDTO.getId()
                + ", IdComanda: "
                + notificaPrepOrdineDTO.getIdComanda() + "}");
    }

}
