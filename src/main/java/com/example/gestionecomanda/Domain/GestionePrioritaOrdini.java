package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GestionePrioritaOrdini implements TestPort{
    private final DataPort dataPort;

    @Autowired
    public GestionePrioritaOrdini(@Qualifier("jpadbAdapter") DataPort dataPort) {
        this.dataPort = dataPort;
    }

    @Override
    public Iterable<ClienteDTO> getClienti() {
        return dataPort.getClienti();
    }
}
