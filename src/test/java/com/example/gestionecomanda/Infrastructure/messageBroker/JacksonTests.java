package com.example.gestionecomanda.Infrastructure.messageBroker;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.util.TestDataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.text.SimpleDateFormat;

import static com.example.gestionecomanda.util.TestUtil.formattedTimestamp;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Questa è una classe di test per testare la serializzazione e deserializzazione via Jackson
 * con l'oggetto ObjectMapper
 */
@JsonTest
@Log
public class JacksonTests {

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    }

    @Test
    public void testSerialization() throws JsonProcessingException {

        OrdineDTO ordineDto = TestDataUtil.createOrdineDtoC();

        // Serializzazione
        String json = objectMapper.writeValueAsString(ordineDto);

        log.info("from: " + ordineDto.toString());
        log.info("to:" + json);

        assertThat(json).isEqualTo("{\"id\":"  + ordineDto.getId()
                + ",\"idComanda\":" +  ordineDto.getIdComanda()
                + ",\"idPiatto\":" +  "\"" + ordineDto.getIdPiatto() + "\""
                + ",\"stato\":" +  ordineDto.getStato()
                + ",\"urgenzaCliente\":" +  ordineDto.getUrgenzaCliente()
                + ",\"tordinazione\":" +  "\"" + formattedTimestamp(ordineDto.getTOrdinazione()) + "\""
                +"}");
    }

    @Test
    public void testDeserialization() throws JsonProcessingException {

        OrdineDTO ordineDto = TestDataUtil.createOrdineDtoC();

        String json = "{\"id\":"  + ordineDto.getId()
                + ",\"idComanda\":" +  ordineDto.getIdComanda()
                + ",\"idPiatto\":" +  "\"" + ordineDto.getIdPiatto() + "\""
                + ",\"stato\":" +  ordineDto.getStato()
                + ",\"urgenzaCliente\":" +  ordineDto.getUrgenzaCliente()
                + ",\"tordinazione\":" +  "\"" + formattedTimestamp(ordineDto.getTOrdinazione()) + "\""
                +"}";

        // Deserializzazione
        OrdineDTO ordineDTO = objectMapper.readValue(json,OrdineDTO.class);

        log.info("from:" + json);
        log.info("to: " + ordineDTO.toString());

        assertThat(ordineDTO).isEqualTo(ordineDto);
    }

}
