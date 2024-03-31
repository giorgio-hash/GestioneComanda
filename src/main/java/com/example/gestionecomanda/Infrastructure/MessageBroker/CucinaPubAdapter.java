package com.example.gestionecomanda.Infrastructure.MessageBroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class CucinaPubAdapter {

    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = (CompletableFuture<SendResult<String, Object>>) template.send("cucina-demo-1", message);
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
