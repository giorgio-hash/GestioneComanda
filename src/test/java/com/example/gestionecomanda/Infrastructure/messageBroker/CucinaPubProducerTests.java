package com.example.gestionecomanda.Infrastructure.messageBroker;

import ch.qos.logback.classic.Logger;
import com.example.gestionecomanda.Infrastructure.MessageBroker.CucinaPubProducer;
import com.example.gestionecomanda.util.TestAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import config.EmbeddedKafkaConfig;
import lombok.extern.java.Log;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


/**
 * Questa classe è un test di integrazione che utilizza un'istanza Embedded Kafka.
 * @EmbeddedKafka inietta una istanza di EmbeddedKafkaBroker nella nostra classe di test.
 * Embedded Kafka è una libreria che fornisce istanze di Kafka e Confluent Schema Registry in memoria
 * per eseguire i test, in modo da non dipendere da un server Kafka esterno.
 *
 * Viene testato specificamente il PRODUCER di kafka, nello specifico la classe CucinaPubService,
 * si inviano messaggi dal nostro producer e si verifica che siano stati inviati sul topic specifico
 * utilizzando un consumer creato con embedded kafka
 */
@EnableKafka
@SpringBootTest()
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EmbeddedKafka(partitions = 1,
        controlledShutdown = false,
        brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=29092" },
        topics = {"${spring.kafka.producer.topic}"})
class CucinaPubProducerTests {

    @Autowired
    private CucinaPubProducer producer;
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;
    @Value("${spring.kafka.producer.topic}")
    private String topic;
    private Logger logger;
    private TestAppender testAppender;

    @BeforeEach
    public void setup() {
        logger = (Logger) LoggerFactory.getLogger(CucinaPubProducer.class);
        testAppender = new TestAppender();
        testAppender.start();
        logger.addAppender(testAppender);
    }

    @Test
    public void testSendingMessage() throws JsonProcessingException {

        String data = "test message";

        producer.send(data);

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testT", "false", embeddedKafka);
        DefaultKafkaConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        Consumer<Integer, String> consumer = cf.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, topic);
        ConsumerRecord<Integer, String> received = KafkaTestUtils.getSingleRecord(consumer, topic);

        // testo il corretto invio
        assertFalse(testAppender.events.isEmpty());
        assertEquals("Sent Message=[\"test message\"] with offset=[0]", testAppender.events.get(0).getFormattedMessage());

        // testo la corretta ricezione
        assertThat(received.offset()).isEqualTo(0);
        assertThat(received.topic()).isEqualTo(topic);
        assertThat(received.value()).contains(data);
        assertThat(received.partition()).isEqualTo(0);

        logger.detachAppender(testAppender);
    }

}