package com.example.gestionecomanda.Domain.mappers;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.mappers.impl.OrdineMapper;
import com.example.gestionecomanda.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * In questa classe di test si verifica il corretto funzionamento dei mapper per la classe OrdineEntity e OrdineDTO
 */
@SpringBootTest
public class OrdineMapperTests {

    @Autowired
    private OrdineMapper ordineMapper;

    @Test
    public void testMapTo(){
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityA();
        OrdineDTO ordineDTO = ordineMapper.mapTo(ordineEntity);
        assertThat(ordineDTO.getId()).isEqualTo(ordineEntity.getId());
        assertThat(ordineDTO.getIdComanda()).isEqualTo(ordineEntity.getIdComanda());
        assertThat(ordineDTO.getStato()).isEqualTo(ordineEntity.getStato());
        assertThat(ordineDTO.getIdPiatto()).isEqualTo(ordineEntity.getIdPiatto());
        assertThat(ordineDTO.getUrgenzaCliente()).isEqualTo(ordineEntity.getUrgenzaCliente());
        assertThat(ordineDTO.getTOrdinazione()).isEqualTo(ordineEntity.getTOrdinazione());
    }

    @Test
    public void testMapFrom(){
        OrdineDTO ordineDTO = TestDataUtil.createOrdineDtoB();
        OrdineEntity ordineEntity = ordineMapper.mapFrom(ordineDTO);
        assertThat(ordineEntity.getId()).isEqualTo(ordineDTO.getId());
        assertThat(ordineEntity.getIdComanda()).isEqualTo(ordineDTO.getIdComanda());
        assertThat(ordineEntity.getStato()).isEqualTo(ordineDTO.getStato());
        assertThat(ordineEntity.getIdPiatto()).isEqualTo(ordineDTO.getIdPiatto());
        assertThat(ordineEntity.getUrgenzaCliente()).isEqualTo(ordineDTO.getUrgenzaCliente());
        assertThat(ordineEntity.getTOrdinazione()).isEqualTo(ordineDTO.getTOrdinazione());
    }

}
