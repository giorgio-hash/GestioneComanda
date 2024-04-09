package com.example.gestionecomanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioneComandaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestioneComandaApplication.class, args);
    }

    // TEST
    // Solo per testare che il message broker funziona, all'avvio invia un messaggio sul topic
    /*
    @Bean
    public ApplicationRunner runner(final CucinaPubAdapter cucinaPubAdapter) {
        return args -> {
            cucinaPubAdapter.sendMessageToTopic("messaggio di default all'avvio");
        };
    }*/

}
