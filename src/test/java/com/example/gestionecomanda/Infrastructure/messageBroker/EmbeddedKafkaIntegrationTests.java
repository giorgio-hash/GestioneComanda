package com.example.gestionecomanda.Infrastructure.messageBroker;

import com.example.gestionecomanda.Infrastructure.MessageBroker.CucinaPubProducer;
import com.example.gestionecomanda.Interface.EventControllers.SubCucinaAdapter.impl.SubCucinaAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import com.example.gestionecomanda.util.TestAppender;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Questa classe è un test di integrazione che utilizza un'istanza Embedded Kafka.
 * @EmbeddedKafka inietta una istanza di EmbeddedKafkaBroker nella nostra classe di test.
 * Embedded Kafka è una libreria che fornisce istanze di Kafka e Confluent Schema Registry in memoria
 * per eseguire i test, in modo da non dipendere da un server Kafka esterno.
 *
 * Il test in questione si basa sull'ivio di un messeggio da parte del producer e si verifica
 * semplicemente che è stato ricevuto del consumer, viene utilizzato un topic specifico di test
 */

@EnableKafka
@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = false, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=29092" })
class EmbeddedKafkaIntegrationTests {

    @Autowired
    private SubCucinaAdapter consumer;

    @Autowired
    private CucinaPubProducer producer;

    @Value("${spring.kafka.test.topic}")
    private String topic;

    private Logger logger;
    private TestAppender testAppender;

    @BeforeEach
    public void setup() {
        consumer.resetLatch();

        logger = (Logger) LoggerFactory.getLogger(SubCucinaAdapter.class);
        testAppender = new TestAppender();
        testAppender.start();
        logger.addAppender(testAppender);
    }

    @Test
    @Order(1)
    public void testLogOutput() throws JsonProcessingException, InterruptedException {

        String data = "test message";

        producer.send(data);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        assertTrue(messageConsumed);

        assertFalse(testAppender.events.isEmpty());
        assertEquals("Received a message \"test message\", from " + topic + " topic, 0 partition, and 0 offset", testAppender.events.get(0).getFormattedMessage());

        logger.detachAppender(testAppender);
    }

    @Test
    @Order(2)
    public void testLogOutput2() throws JsonProcessingException, InterruptedException {

        String data = "test message2";

        producer.send(data);

        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

        assertTrue(messageConsumed);

        assertFalse(testAppender.events.isEmpty());
        assertEquals("Received a message \"test message2\", from " + topic + " topic, 0 partition, and 1 offset", testAppender.events.get(0).getFormattedMessage());

        logger.detachAppender(testAppender);
    }

}