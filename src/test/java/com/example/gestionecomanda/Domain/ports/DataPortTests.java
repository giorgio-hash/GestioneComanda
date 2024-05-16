package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe di test di integrazione della DataPort con operazioni CRUD sul database
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DataPortTests {

    private DataPort dataPort;

    @Autowired
    public DataPortTests(DataPort dataPort) {
        this.dataPort = dataPort;
    }

    @Test
    public void testSaveOrderAndFindOrderById() {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrdineEntity = dataPort.saveOrder(ordineEntity);
        Optional<OrdineEntity> result = dataPort.getOrderById(savedOrdineEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedOrdineEntity);
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getIdComanda()).isEqualTo(ordineEntity.getIdComanda());
        assertThat(result.get().getStato()).isEqualTo(ordineEntity.getStato());
        assertThat(result.get().getIdPiatto()).isEqualTo(ordineEntity.getIdPiatto());
        assertThat(result.get().getTOrdinazione()).isEqualTo(ordineEntity.getTOrdinazione());
    }

    @Test
    public void testMultipleSaveOrderAndFindByIdComanda(){
        OrdineEntity ordineEntityA = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrdineEntityA = dataPort.saveOrder(ordineEntityA);
        OrdineEntity ordineEntityB = TestDataUtil.createOrdineEntityB();
        OrdineEntity savedOrdineEntityB = dataPort.saveOrder(ordineEntityB);
        OrdineEntity ordineEntityC = TestDataUtil.createOrdineEntityC();
        OrdineEntity savedOrdineEntityC = dataPort.saveOrder(ordineEntityC);

        Iterable<OrdineEntity> result = dataPort.findAllOrdersByIdComanda(ordineEntityA.getIdComanda());
        assertThat(result)
                .hasSize(2).
                containsExactly(savedOrdineEntityA, savedOrdineEntityB);
    }

    @Test
    public void testOrderPartialUpdate() {
        OrdineEntity ordineEntity = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrdineEntity = dataPort.saveOrder(ordineEntity);
        savedOrdineEntity.setStato(0);
        savedOrdineEntity.setUrgenzaCliente(1);
        dataPort.saveOrder(savedOrdineEntity);
        Optional<OrdineEntity> result = dataPort.getOrderById(savedOrdineEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedOrdineEntity);
    }
/*
    @Test
    public void testDeleteOrder() {
        OrdineEntity ordineEntityA = TestDataUtil.createOrdineEntityA();
        OrdineEntity savedOrdineEntityA = dataPort.saveOrder(ordineEntityA);
        dataPort.deleteOrder(savedOrdineEntityA.getId());
        Optional<OrdineEntity> result = dataPort.getOrderById(savedOrdineEntityA.getId());
        assertThat(result).isEmpty();
    }
*/

}
