package com.example.gestionecomanda.Interface.EventControlles.testControllers;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.ports.DataPort;
import com.example.gestionecomanda.Interface.testControllers.TestService;
import com.example.gestionecomanda.util.TestDataUtil;
import com.example.gestionecomanda.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.gestionecomanda.util.TestUtil.formattedTimestamp;

/**
 * Test di Integrazione della classe TestControllers usando MockMVC
 * MockMvc permette di testare i controller in un ambiente simulato senza avviare un server.
 */
@SpringBootTest
@EnableKafka
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
    @Autowired
    private DataPort dataPort;
    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;
    @Value("${spring.kafka.consumer.gestioneCliente.topic}")
    private String topic_notifyOrderEvent;
    @Value("${spring.kafka.consumer.gestioneCucina.topic}")
    private String topic_notifyPrepEvent;
    @Value("${spring.kafka.producer.topic}")
    private String topic_sendOrderEvent;


    /* TEST URL:            */

    /* /test/order */

    @Test
    public void testThatAddOrderSuccessfullyReturnsHttp201Created() throws Exception {
        OrdineEntity ordineEntityA = TestDataUtil.createOrdineEntityA();
        String ordineJson = objectMapper.writeValueAsString(ordineEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatAddOrderSuccessfullyReturnsOrderDTO() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoA();
        String ordineJson = objectMapper.writeValueAsString(ordineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineDTO.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineDTO.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.stato").value(ordineDTO.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(ordineDTO.getUrgenzaCliente()));
    }

    @Test
    public void testThatGetOrderReturnsHttpStatus200WhenOrderExist() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityB();
        dataPort.saveOrder(ordineEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetOrderReturnsOrderWhenOrderExist() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityB();
        dataPort.saveOrder(ordineEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineEntity.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineEntity.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.stato").value(ordineEntity.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(ordineEntity.getUrgenzaCliente()));
    }

    @Test
    public void testThatGetOrderReturnsHttpStatus404WhenNoOrderExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/order/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatPartialUpdateExistingOrderReturnsHttpStatus20Ok() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityC();
        OrdineEntity savedOrder = dataPort.saveOrder(ordineEntity);

        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoC();
        ordineDTO.setStato(1);
        String authorDtoJson = objectMapper.writeValueAsString(ordineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/test/order/" + savedOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateExistingOrderReturnsUpdatedOrder() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityC();
        OrdineEntity savedOrder = dataPort.saveOrder(ordineEntity);

        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoC();
        ordineDTO.setStato(1);
        ordineDTO.setUrgenzaCliente(2);
        String authorDtoJson = objectMapper.writeValueAsString(ordineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/test/order/" + savedOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedOrder.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineEntity.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineEntity.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.stato").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.tordinazione").value(formattedTimestamp(savedOrder.getTOrdinazione()))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(2));
    }

    @Test
    public void testThatListOrdersByIdComandaReturnsHttpStatus200() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityC();
        OrdineEntity savedOrder = dataPort.saveOrder(ordineEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/orders/" + savedOrder.getIdComanda())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListOrdersByIdComandaReturnsHttpStatus404() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/orders/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatListOrdersBiIdComandaReturnsListOfOrders() throws Exception {
        OrdineEntity ordineEntityA = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrderA = dataPort.saveOrder(ordineEntityA);

        OrdineEntity ordineEntityB = TestDataUtil.createOrdineEntityB();
        OrdineEntity savedOrderB = dataPort.saveOrder(ordineEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/orders/" + ordineEntityA.getIdComanda())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(savedOrderA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].idComanda").value(savedOrderA.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].idPiatto").value(savedOrderA.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].stato").value(savedOrderA.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].tordinazione").value(formattedTimestamp(savedOrderA.getTOrdinazione()))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].urgenzaCliente").value(savedOrderA.getUrgenzaCliente())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(savedOrderB.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].idComanda").value(savedOrderB.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].idPiatto").value(savedOrderB.getIdPiatto())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].stato").value(savedOrderB.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].tordinazione").value(formattedTimestamp(savedOrderB.getTOrdinazione()))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].urgenzaCliente").value(savedOrderB.getUrgenzaCliente())
        );
    }

    @Test
    public void testThatDeleteOrderReturnsHttpStatus204ForNonExistingOrder() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/test/order/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteOrderReturnsHttpStatus204ForExistingOrder() throws Exception {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrder = dataPort.saveOrder(ordineEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/test/order/" + savedOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /* /test/sendorderevent */

    @Test
    public void testThatSendMessageToTopicSendOrderEventSuccessfullyReturnsHttp201Created() throws Exception {
        OrdineEntity ordineEntityB = TestDataUtil.createOrdineEntityA();
        String ordineJson = objectMapper.writeValueAsString(ordineEntityB);

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
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoC();
        String ordineJson = objectMapper.writeValueAsString(ordineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ordineJson)
        /*).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(ordineDTO.getId())  l'id viene auto generato */
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(ordineDTO.getIdComanda())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idPiatto").value(ordineDTO.getIdPiatto())
        ).andExpect(
                 MockMvcResultMatchers.jsonPath("$.stato").value(ordineDTO.getStato())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.urgenzaCliente").value(ordineDTO.getUrgenzaCliente()));
    }

    @Test
    public void testThatPeekMessageFromTopicSendOrderEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoB();
        String ordineDto = TestUtil.serialize(ordineDTO);
        TestUtil.sendMessageToTopic(topic_sendOrderEvent,ordineDto,embeddedKafka);

        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/sendorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicSendOrderEventReturnsOrderWhenOrderExist() throws Exception {
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoC();
        String ordineDto = TestUtil.serialize(ordineDTO);

        TestUtil.sendMessageToTopic(topic_sendOrderEvent,ordineDto,embeddedKafka);
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
        NotificaOrdineDTO notificaOrdineDTO = TestDataUtil.createNotificaOrdineDTOA();
        String notifica = TestUtil.serialize(notificaOrdineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notifica)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyOrderEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        NotificaOrdineDTO notificaOrdineDTO = TestDataUtil.createNotificaOrdineDTOA();
        String notifica = TestUtil.serialize(notificaOrdineDTO);
        TestUtil.sendMessageToTopic(topic_notifyOrderEvent,notifica,embeddedKafka);

        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyOrderEventReturnsOrderWhenOrderExist() throws Exception {
        NotificaOrdineDTO notificaOrdineDTO = TestDataUtil.createNotificaOrdineDTOA();
        String notifica = TestUtil.serialize(notificaOrdineDTO);
        TestUtil.sendMessageToTopic(topic_notifyOrderEvent,notifica,embeddedKafka);
        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyorderevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(notificaOrdineDTO.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.idComanda").value(notificaOrdineDTO.getIdComanda()));
    }

    /* /test/notifyprepevent */

    @Test
    public void testThatSendMessageToTopicNotifyPrepEventSuccessfullyReturnsHttp201Created() throws Exception {
        NotificaPrepOrdineDTO notificaPrepOrdineDTO = TestDataUtil.createNotificaPrepOrdineDTOA();
        String notifica = TestUtil.serialize(notificaPrepOrdineDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notifica)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyPrepEventReturnsHttpStatus200WhenOrderExist() throws Exception {
        OrdineEntity existing = TestDataUtil.createOrdineEntityA();
        existing.setId(1);
        dataPort.saveOrder(existing);//dato su cui viene eseguito l'update

        NotificaPrepOrdineDTO notificaPrepOrdineDTO = TestDataUtil.createNotificaPrepOrdineDTOA();
        String notifica = TestUtil.serialize(notificaPrepOrdineDTO);
        TestUtil.sendMessageToTopic(topic_notifyPrepEvent,notifica,embeddedKafka);

        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPeekMessageFromTopicNotifyPrepEventReturnsOrderWhenOrderExist() throws Exception {

        OrdineEntity existing = TestDataUtil.createOrdineEntityA();
        existing.setId(1);
        dataPort.saveOrder(existing);//dato su cui viene eseguito l'update

        NotificaPrepOrdineDTO notificaPrepOrdineDTO = TestDataUtil.createNotificaPrepOrdineDTOA();
        String notifica = TestUtil.serialize(notificaPrepOrdineDTO);
        TestUtil.sendMessageToTopic(topic_notifyPrepEvent,notifica,embeddedKafka);

        Thread.sleep(5000);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/test/notifyprepevent")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.content().string(notifica));
    }

}
