package com.example.gestionecomanda.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    /**
     * ModelMapper è una libreria di mappatura generica che può essere utilizzata per mappare oggetti di qualsiasi tipo
     *
     * La strategia di corrispondenza LOOSE in ModelMapper ignora le differenze di maiuscole/minuscole e gli underscore
     * nei nomi dei campi, considera i campi come corrispondenti anche se i nomi dei campi nell’oggetto sorgente
     * e nell’oggetto destinazione non sono esattamente gli stessi, ma contengono le stesse parole.
    */
    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }

}
