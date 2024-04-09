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

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class KafkaMockProducerAndConsumerTests {

    private MockProducer<String, String> mockProducer;
    private MockConsumer<String, String> mockConsumer;

    @BeforeEach
    public void setUp() {
        mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());
        mockConsumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);
    }

    @Test
    public void testKafkaProducerConsumer() {
        String message = "test message";
        String topic = "testTopic";

        // Simulate sending a message
        mockProducer.send(new ProducerRecord<>(topic, message));
        mockProducer.flush();
        log.info("Producer sending: " + message);

        // Simulate consuming the message
        mockConsumer.assign(Collections.singletonList(new TopicPartition(topic, 0)));
        mockConsumer.updateBeginningOffsets(Collections.singletonMap(new TopicPartition(topic, 0), 0L));
        mockConsumer.addRecord(new ConsumerRecord<>(topic, 0, 0L, null, message));

        ConsumerRecord<String, String> consumedRecord = mockConsumer.poll(Duration.ofMillis(1000)).iterator().next();
        log.info("Consumer receiving: " + message);

        assertThat(consumedRecord).isNotNull();
        assertThat(consumedRecord.value()).isEqualTo(message);
    }
}
