package com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface NotifyPrepEvent {
    void receive(ConsumerRecord<?, ?> consumerRecord);
}
