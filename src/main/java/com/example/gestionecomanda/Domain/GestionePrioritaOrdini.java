package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.ports.ClientePort;
import com.example.gestionecomanda.Domain.ports.DataPort;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class GestionePrioritaOrdini implements ClientePort {
    private final DataPort dataPort;
    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyPrepEvent;

    @Autowired
    public GestionePrioritaOrdini(DataPort dataPort) {
        this.dataPort = dataPort;
    }

    @Override
    public void notifyOrder(NotificaOrdineDTO notificaOrdineDTO) {
        log.info("Received a notify from "
                + topic_notifyPrepEvent
                + " : { id: " + notificaOrdineDTO.getId()
                + ", IdComanda: "
                + notificaOrdineDTO.getIdComanda() + "}");
    }

}
