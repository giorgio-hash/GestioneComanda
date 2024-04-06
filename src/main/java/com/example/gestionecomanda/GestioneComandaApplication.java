package com.example.gestionecomanda;

import com.example.gestionecomanda.Infrastructure.MessageBroker.CucinaPubAdapter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestioneComandaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestioneComandaApplication.class, args);
    }

    // TEST
    // Solo per testare che il message broker funziona, all'avvio invia un messaggio sul topic
    @Bean
    public ApplicationRunner runner(final CucinaPubAdapter cucinaPubAdapter) {
        return args -> {
            cucinaPubAdapter.sendMessageToTopic("messaggio di default all'avvio");
        };
    }

}
