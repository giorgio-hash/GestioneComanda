package com.example.gestionecomanda.Infrastructure.messageBroker;

import lombok.extern.java.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log
public class KafkaMockCucinaPubProducerAndConsumerTests {

    private MockProducer<String, String> mockProducer;
    private MockConsumer<String, String> mockConsumer;

    @Value("${spring.kafka.test.topic}")
    private String topic;

    @BeforeEach
    public void setUp() {
        mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());
        mockConsumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);
    }

    @Test
    public void testKafkaProducerConsumer() {
        String message = "test message";
        //String topic = "testTopic";

        // Simulo l'invio di un messaggio
        mockProducer.send(new ProducerRecord<>(topic, message));
        mockProducer.flush();
        log.info("Producer sending: " + message);

        // Simulo la ricezione di un messaggio
        mockConsumer.assign(Collections.singletonList(new TopicPartition(topic, 0)));
        mockConsumer.updateBeginningOffsets(Collections.singletonMap(new TopicPartition(topic, 0), 0L));
        mockConsumer.addRecord(new ConsumerRecord<>(topic, 0, 0L, null, message));

        ConsumerRecord<String, String> consumedRecord = mockConsumer.poll(Duration.ofMillis(1000)).iterator().next();
        log.info("Consumer receiving: " + message);

        assertThat(consumedRecord).isNotNull();
        assertThat(consumedRecord.value()).isEqualTo(message);
    }
}
