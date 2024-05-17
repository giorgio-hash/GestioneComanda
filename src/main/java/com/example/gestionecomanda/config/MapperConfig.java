package com.example.gestionecomanda.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe configurazione di ModelMapper utile per mappare oggetti di qualsiasi tipo (tra Entita' a DTO e viceversa)
 */
@Configuration
public class MapperConfig {

    /**
     * La strategia di corrispondenza LOOSE in ModelMapper ignora le differenze di maiuscole/minuscole e gli underscore
     * nei nomi dei campi, considera i campi come corrispondenti anche se i nomi dei campi nell’oggetto sorgente
     * e nell’oggetto destinazione non sono esattamente gli stessi, ma contengono le stesse parole.
     * @return istanza di Model mapper.
    */
    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}
