package com.example.gestionecomanda.Interface.testControllers;

import com.example.gestionecomanda.Domain.ports.DataPort;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.ports.TestPort;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.mappers.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementazione del REST Controller seguendo lo stile Interface Driven Controller
 */
@RestController
public class TestController implements TestPort{

    private TestService testService;
    private Mapper<OrdineEntity, OrdineDTO> ordineMapper;
    private DataPort dataPort;

    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyOrderEvent;

    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;

    @Autowired
    public TestController(TestService testService, Mapper<OrdineEntity, OrdineDTO> ordineMapper, DataPort dataPort) {
        this.testService = testService;
        this.ordineMapper = ordineMapper;
        this.dataPort = dataPort;
    }

    @Override
    public ResponseEntity<OrdineDTO> addOrdine(OrdineDTO ordineDTO) {
        OrdineEntity ordineEntity = ordineMapper.mapFrom(ordineDTO);
        OrdineEntity savedOrdineEntity = dataPort.saveOrder(ordineEntity);
        OrdineDTO savedOrdineDTO = ordineMapper.mapTo(savedOrdineEntity);
        return new ResponseEntity<>(savedOrdineDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrdineDTO> getOrdine(int id) {
        Optional<OrdineEntity> ordineEntity = dataPort.getOrderById(id);
        if(ordineEntity.isPresent()){
            OrdineDTO ordineDTO = ordineMapper.mapTo(ordineEntity.get());
            return new ResponseEntity<>(ordineDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<OrdineDTO> partialUpdateOrdine(@PathVariable int id, @RequestBody OrdineDTO ordineDTO) {
        if(!dataPort.isOrderExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        OrdineEntity ordineEntity = ordineMapper.mapFrom(ordineDTO);
        OrdineEntity updatedEntity = dataPort.updateOrder(id, ordineEntity);

        return new ResponseEntity<>(ordineMapper.mapTo(updatedEntity),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrdineDTO>> getAllOrdersByIdComanda(int idComanda) {
        List<OrdineEntity> ordini = dataPort.findAllOrdersByIdComanda(idComanda);
        if(!ordini.isEmpty())
            return new ResponseEntity<>(ordini.stream()
                    .map(ordineMapper::mapTo)
                    .collect(Collectors.toList()),HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity deleteOrder(@PathVariable("id") int id) {
        dataPort.deleteOrder(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<OrdineDTO> sendOrderEvent(@RequestBody OrdineDTO ordineDTO) throws JsonProcessingException {
        OrdineEntity ordineEntity = ordineMapper.mapFrom(ordineDTO);
        OrdineEntity savedOrdineEntity = dataPort.saveOrder(ordineEntity);
        OrdineDTO savedOrdineDTO = ordineMapper.mapTo(savedOrdineEntity);
        testService.sendMessageToTopicSendOrderEvent(savedOrdineDTO);
        return new ResponseEntity<>(savedOrdineDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> getMessageFromTopicSendOrderEvent() {
        Optional<String> message = testService.peekFromSendOrderEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> sendNotifyOrderEvent(@RequestBody String message) throws JsonProcessingException {
        testService.sendMessageToTopic(message, topic_notifyOrderEvent);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> getMessageFromTopicNotifyOrderEvent() {
        Optional<String> message = testService.peekFromNotifyOrderEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> sendNotifyPrepEvent(@RequestBody String message) throws JsonProcessingException {
        testService.sendMessageToTopic(message, topic_notifyPrepEvent);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> getMessageFromTopicNotifyPrepEvent() {
        Optional<String> message = testService.peekFromNotifyPrepEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /*
    @Override
    public Iterable<ClienteEntity> getClienti(){
        return null;
    }
    */

}
