package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;

public interface DataPort {
    void getOrderData();
    void setOrderStatus();

    Iterable<ClienteEntity> getClienti();

    /**
     * Salva l'entita' ordine all'interno del database
     *
     * @param ordineEntity entità ordine da salvare
     * @return entita' ordine salvata
     */
    OrdineEntity save(OrdineEntity ordineEntity);
}
