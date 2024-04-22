package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.ports.DataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionePrioritaOrdini {
    private final DataPort dataPort;

    @Autowired
    public GestionePrioritaOrdini(DataPort dataPort) {
        this.dataPort = dataPort;
    }


}
