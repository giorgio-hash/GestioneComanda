package com.example.gestionecomanda.Infrastructure.messageBroker;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.util.TestDataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Questa è una classe di test per testare la serializzazione e deserializzazione via Jackson
 * con l'oggetto ObjectMapper
 */
@JsonTest
public class JacksonTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialization() throws JsonProcessingException {

        OrdineDTO ordineDto = TestDataUtil.createOrdineDtoA();

        // Serializzazione
        String json = objectMapper.writeValueAsString(ordineDto);

        assertThat(json).isEqualTo("{\"id\":"  + ordineDto.getId() + ",\"idComanda\":"+  ordineDto.getIdComanda() +"}");
    }

    @Test
    public void testDeserialization() throws JsonProcessingException {

        OrdineDTO ordineDto = TestDataUtil.createOrdineDtoA();

        String json = "{\"id\":"  + ordineDto.getId() + ",\"idComanda\":"+  ordineDto.getIdComanda() +"}";

        // Deserializzazione
        OrdineDTO ordineDTO = objectMapper.readValue(json,OrdineDTO.class);

        assertThat(ordineDTO).isEqualTo(ordineDto);
    }

}
