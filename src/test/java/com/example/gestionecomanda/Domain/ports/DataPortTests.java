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
        Optional<OrdineEntity> result = dataPort.findOrderById(savedOrdineEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedOrdineEntity);
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getIdComanda()).isEqualTo(ordineEntity.getIdComanda());
        assertThat(result.get().getStato()).isEqualTo(ordineEntity.getStato());
        assertThat(result.get().getIdPiatto()).isEqualTo(ordineEntity.getIdPiatto());
        assertThat(result.get().getTOrdinazione()).isEqualTo(ordineEntity.getTOrdinazione());
    }

}
