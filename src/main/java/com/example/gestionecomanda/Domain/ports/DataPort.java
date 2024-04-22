package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;

import java.util.Optional;

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
    OrdineEntity saveOrder(OrdineEntity ordineEntity);

    /**
     * Cerca nel db e restituisce l'ordine corrispondente all' id dato
     *
     * @param id id dell'ordine
     * @return Optional(OrdineEntity) se esiste altrimenti Optional(null)
     */
    Optional<OrdineEntity> findOrderById(int id);
}
