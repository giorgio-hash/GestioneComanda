package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.ports.ClientePort;
import com.example.gestionecomanda.Domain.ports.DataPort;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class GestionePrioritaOrdini implements ClientePort {
    private final DataPort dataPort;
    private final AlgIF algIF;
    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyPrepEvent;

    @Autowired
    public GestionePrioritaOrdini(DataPort dataPort, AlgIF algIF) {
        this.dataPort = dataPort;
        this.algIF = algIF;
    }

    @Override
    public void notifyOrder(NotificaOrdineDTO notificaOrdineDTO) {
        log.info("Received a notify from "
                + topic_notifyPrepEvent
                + " : { id: " + notificaOrdineDTO.getId()
                + ", IdComanda: "
                + notificaOrdineDTO.getIdComanda() + "}");

        extractOrderAndSendToHeap(notificaOrdineDTO);
    }

    private void extractOrderAndSendToHeap(NotificaOrdineDTO notificaOrdineDTO){

        Optional<OrdineEntity> no = dataPort.getOrderById(notificaOrdineDTO.getId());
        if(no.isPresent()){
            log.info( "pushing " + no.get() + "to heap!");
            algIF.pushNewOrder(no.get());
        }else
            log.info( "oggetto Ordine non trovato!!");
    }

}
