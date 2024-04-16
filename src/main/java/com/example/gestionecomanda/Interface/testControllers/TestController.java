package com.example.gestionecomanda.Interface.testControllers;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;
import com.example.gestionecomanda.Domain.TestPort;
import com.example.gestionecomanda.Interface.testControllers.impl.TestServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    private TestService testService;
    private TestPort testport;

    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyOrderEvent;

    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;

    @Autowired
    public TestController(TestServiceImpl testService, @Qualifier("gestionePrioritaOrdini") TestPort testport) {
        this.testService = testService;
        this.testport = testport;
    }


    /**
     * Espone una API di GET con la quale è possibile richiedere al database tutti i clienti
     * @return una lista di oggetti Cliente serializzati
     */
    @GetMapping(path = "/test/clienti")
    public Iterable<ClienteDTO> getClienti(){
        return testport.getClienti();
    }


    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic sendOrderEvent da gestione comanda verso gestione cucina
     *
     * @param message contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/test/sendorderevent")
    public ResponseEntity<String> sendMessageToTopicSendOrderEvent(@RequestBody String message) throws JsonProcessingException {
        testService.sendMessageToTopicSendOrderEvent(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic SendOrderEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     */
    @GetMapping(path = "/test/sendorderevent")
    public ResponseEntity<String> peekMessageFromTopicSendOrderEvent() {
        Optional<String> message = testService.peekFromSendOrderEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic notifyOrderEvent da gestione cliente verso gestione comanda
     *
     * @param message contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/test/notifyorderevent")
    public ResponseEntity<String> sendMessageToTopicNotifyOrderEvent(@RequestBody String message) throws JsonProcessingException {
        testService.sendMessageToTopic(message, topic_notifyOrderEvent);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic NotifyOrderEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     */
    @GetMapping(path = "/test/notifyorderevent")
    public ResponseEntity<String> peekMessageFromTopicNotifyOrderEvent() {
        Optional<String> message = testService.peekFromNotifyOrderEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Espone una API di POST con la quale è possibile iniettare all'interno del broker oggetti al fine di test
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @param message contenuto dell'oggetto da iniettare
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     * @throws JsonProcessingException eccezione sollevata dalla serializzazione
     */
    @PostMapping(path = "/test/notifyprepevent")
    public ResponseEntity<String> sendMessageToTopicNotifyPrepEvent(@RequestBody String message) throws JsonProcessingException {
        testService.sendMessageToTopic(message, topic_notifyPrepEvent);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Espone una API di GET con la quale è possibile ottenere l'ultimo messaggio letto sul topic NotifyPrepEvent
     * Si testa il topic notifyPrepEvent da gestione cucina verso gestione comanda
     *
     * @return entità risposta che contiene l'oggetto creato e una risposta HTTP associata
     */
    @GetMapping(path = "/test/notifyprepevent")
    public ResponseEntity<String> peekMessageFromTopicNotifyPrepEvent() {
        Optional<String> message = testService.peekFromNotifyPrepEvent();
        if(message.isPresent())
            return new ResponseEntity<>(message.get(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
