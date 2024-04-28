package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.mappers.impl.OrdineMapper;
import com.example.gestionecomanda.Domain.ports.CucinaPort;
import com.example.gestionecomanda.Domain.ports.DataPort;
import com.example.gestionecomanda.Domain.ports.MessagePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class GestioneHeap implements CucinaPort, AlgIF {

    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;
    private final OrdineMapper ordineMapper;
    private final MessagePort<OrdineDTO> messagePort;
    private final DataPort dataPort;

    //TODO
    //per entrare nella PriorityQueue,
    // la classe che andremo ad utilizzare come oggetto ordine avrà bisogno di implementare Comparable!
    //private final Queue<OrdineEntity> queue;
    private OrdineEntity o;

    @Autowired
    public GestioneHeap(OrdineMapper ordineMapper, MessagePort<OrdineDTO> messagePort, DataPort dataPort) {
        this.ordineMapper = ordineMapper;
        this.messagePort = messagePort;
        this.dataPort = dataPort;
    }

    @Override
    public void setPreparationStatus(NotificaPrepOrdineDTO notificaPrepOrdineDTO) {
        log.info("Received a notify from "
                + topic_notifyPrepEvent
                + " : { id: " + notificaPrepOrdineDTO.getId()
                + ", IdComanda: "
                + notificaPrepOrdineDTO.getIdComanda() + "}");

        OrdineEntity updated = dataPort.updateOrder(notificaPrepOrdineDTO.getId(),OrdineEntity.builder().stato(1).build());
        log.info("Updated: " + updated);
    }

    @Override
    public void pushNewOrder(OrdineEntity ordineEntity) {
        o = ordineEntity;
        try {
            popTopOrder();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void popTopOrder() throws JsonProcessingException {

        if(o != null) sendOrdineToCucina(ordineMapper.mapTo(o));
        else log.info("Qualcosa è andato storto: nessun ordine da mandare in cucina!");

        o = null;
    }

    private void sendOrdineToCucina(OrdineDTO ordineDTO) throws JsonProcessingException {

        messagePort.send(ordineDTO);

    }

}
