package com.example.gestionecomanda.Domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GestionePrioritaOrdini {

    @Autowired
    @Qualifier("JPADBAdapter")
    private DataPort dataPort;
}
