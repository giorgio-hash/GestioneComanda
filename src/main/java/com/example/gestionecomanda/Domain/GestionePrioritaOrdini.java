package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionePrioritaOrdini implements TestPort{
    private final DataPort dataPort;

    @Autowired
    public GestionePrioritaOrdini(DataPort dataPort) {
        this.dataPort = dataPort;
    }

    @Override
    public Iterable<ClienteEntity> getClienti() {
        return dataPort.getClienti();
    }
}
