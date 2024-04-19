package com.example.gestionecomanda.Interface.EventControlles.testControllers;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Interface.testControllers.TestService;
import com.example.gestionecomanda.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static com.example.gestionecomanda.util.TestUtil.formattedTimestamp;


/* TODO: mentre le chiamate GET e POST sul topic SendOrderEvent utilizzano già un oggetto ordine,
 *       gli altri due topic utilizzano ancora una stringa --> sostituire la stringa con un oggetto adeguato
 */

/**
 * Test di Integrazione della classe TestControllers usando MockMVC
 * MockMvc permette di testare i controller in un ambiente simulato senza avviare un server.
 */
@SpringBootTest
@EnableKafka
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        controlledShutdown = false,
        brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" },
        topics = {"${spring.kafka.producer.topic}",
                "${spring.kafka.consumer.gestioneCliente.topic}" ,
                "${spring.kafka.consumer.gestioneCucina.topic}" })
@AutoConfigureMockMvc
public class TestControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestService testService;
    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyOrderEvent;
    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;


    /* TEST URL:            */
    /* /test/sendorderevent */

    @Test
    public void testThatSendMessageToTopicSendOrderEventSuccessfullyReturnsHttp201Created() throws Exception {
        OrdineEntity ordineEntityA = TestDataUtil.createOrdineEntityA();
        String ordineJson = objectMapper.writeValueAsString(ordineEntityA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatSendMessageToTopicSendOrderEventSuccessfullyReturnsOrdineDTO() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoA();
        String ordineJson = objectMapper.writeValueAsString(ordineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(ordineDTO.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineDTO.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineDTO.getIdPiatto())
        ).andExpect(
                 MockMvcResultMatchers.jsonPath("$.stato").value(ordineDTO.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.tordinazione").value(formattedTimestamp(ordineDTO.getTOrdinazione()))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(ordineDTO.getUrgenzaCliente()));
    }

    @Test
    public void testThatPeekMessageFromTopicSendOrderEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoA();
        testService.sendMessageToTopicSendOrderEvent(ordineDTO);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicSendOrderEventReturnsOrderWhenOrderExist() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoA();
        testService.sendMessageToTopicSendOrderEvent(ordineDTO);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(ordineDTO.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineDTO.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineDTO.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.stato").value(ordineDTO.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.tordinazione").value(formattedTimestamp(ordineDTO.getTOrdinazione()))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(ordineDTO.getUrgenzaCliente()));
    }

    /* /test/notifyorderevent */

    @Test
    public void testThatSendMessageToTopicNotifyOrderEventSuccessfullyReturnsHttp201Created() throws Exception {
        String notifica = "notifica di test";
        String ordineJson = objectMapper.writeValueAsString(notifica);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyOrderEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        String notifica = "notifica di test";
        testService.sendMessageToTopic(notifica, topic_notifyOrderEvent);

        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyOrderEventReturnsOrderWhenOrderExist() throws Exception {
        String notifica = "notifica di test";
        testService.sendMessageToTopic(notifica, topic_notifyOrderEvent);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.content().string("\"" + notifica + "\""));
    }

    /* /test/notifyprepevent */

    @Test
    public void testThatSendMessageToTopicNotifyPrepEventSuccessfullyReturnsHttp201Created() throws Exception {
        String notifica = "notifica di test";
        String ordineJson = objectMapper.writeValueAsString(notifica);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyPrepEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        String notifica = "notifica di test";
        testService.sendMessageToTopic(notifica, topic_notifyPrepEvent);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyPrepEventReturnsOrderWhenOrderExist() throws Exception {
        String notifica = "notifica di test";
        testService.sendMessageToTopic(notifica, topic_notifyPrepEvent);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.content().string("\"" + notifica + "\""));
    }



}
