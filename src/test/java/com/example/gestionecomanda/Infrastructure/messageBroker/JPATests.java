package com.example.gestionecomanda.Infrastructure.messageBroker;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity2;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity2PK;
import com.example.gestionecomanda.Domain.Repository.OrdineEntity2Repository;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MariaDBContainer;
import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.*;


@SpringBootTest
public class JPATests{

    @Autowired
    private OrdineEntity2Repository oe2r;

    @ClassRule
    public static MariaDBContainer testcontainer = new MariaDBContainer("mariadb:latest")
            .withDatabaseName("integration-test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",testcontainer::getJdbcUrl);
        registry.add("spring.datasource.username",testcontainer::getUsername);
        registry.add("spring.datasource.password",testcontainer::getPassword);
    }

    @BeforeAll
    static void beforeAll(){
        testcontainer.start();
    }

    @AfterAll
    static void afterAll(){
        testcontainer.stop();
    }

    @Test
    public void insert_ordine(){
        OrdineEntity2 ordine = new OrdineEntity2(1,1,"cavoli",1, Timestamp.from(Instant.now()), 0 );
        oe2r.save(ordine);
        OrdineEntity2 ordinestratto = (oe2r.findById(new OrdineEntity2PK(1,1))).get();
        assertThat(ordinestratto).isEqualTo(ordine);

    }
}
