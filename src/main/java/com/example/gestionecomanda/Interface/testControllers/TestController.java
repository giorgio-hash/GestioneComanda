package com.example.gestionecomanda.Interface.testControllers;

import com.example.gestionecomanda.Infrastructure.MessageBroker.CucinaPubProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private CucinaPubProducer cucinaPubProducer;

    @Autowired
    public TestController(CucinaPubProducer cucinaPubProducer) {
        this.cucinaPubProducer = cucinaPubProducer;
    }

    @PostMapping(path = "/test/cucinaproducerconsumer")
    public ResponseEntity<String> sendMessageToTopic(@RequestBody String message) throws JsonProcessingException {
        cucinaPubProducer.send(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

}
