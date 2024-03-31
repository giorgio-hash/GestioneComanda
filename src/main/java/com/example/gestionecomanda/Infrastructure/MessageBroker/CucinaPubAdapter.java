package com.example.gestionecomanda.Infrastructure.MessageBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CucinaPubAdapter {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("cucina-demo-1", message).completable();
        future.whenComplete((result,ex)->{
            if(ex == null){
                System.out.println("Sent Message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            else{
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
