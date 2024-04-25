package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.mappers.impl.OrdineMapper;
import com.example.gestionecomanda.Domain.ports.CucinaPort;
import com.example.gestionecomanda.Domain.ports.MessagePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;
import java.util.Queue;

@Service
@Log
public class GestioneHeap implements CucinaPort, AlgIF {

    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;
    private final OrdineMapper ordineMapper;
    private final MessagePort<OrdineDTO> messagePort;
    private final Queue<OrdineEntity> queue;

    @Autowired
    public GestioneHeap(OrdineMapper ordineMapper, MessagePort<OrdineDTO> messagePort) {
        this.ordineMapper = ordineMapper;
        this.messagePort = messagePort;
        this.queue = new PriorityQueue<>();
    }

    @Override
    public void setPreparationStatus(NotificaPrepOrdineDTO notificaPrepOrdineDTO) {
        log.info("Received a notify from "
                + topic_notifyPrepEvent
                + " : { id: " + notificaPrepOrdineDTO.getId()
                + ", IdComanda: "
                + notificaPrepOrdineDTO.getIdComanda() + "}");

        //TODO
        //setOrderStatus(notificaPrepOrdineDTO);
    }

    //TODO
    @Override
    public void setOrderStatus(NotificaOrdineDTO notificaOrdineDTO) {


    }

    @Override
    public void pushNewOrder(OrdineEntity ordineEntity) {
        queue.add(ordineEntity);
        try {
            popTopOrder();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void popTopOrder() throws JsonProcessingException {

        OrdineEntity top_order = queue.poll();
        if(top_order != null) sendOrdineToCucina(ordineMapper.mapTo(top_order));
        else log.info("priorityQueue vuota");
    }

    private void sendOrdineToCucina(OrdineDTO ordineDTO) throws JsonProcessingException {

        messagePort.send(ordineDTO);

    }

}
