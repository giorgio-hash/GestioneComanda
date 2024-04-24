package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;

import java.util.List;
import java.util.Optional;

public interface DataPort {

    /**
     * Controlla se l'ordine esiste nel database
     *
     * @param id id dell'entità ordine da controllare la presenza
     * @return true se esiste, false altrimenti
     */
    boolean isOrderExist(int id);

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
    Optional<OrdineEntity> getOrderById(int id);

    /**
     * Aggiorna l'attributo stato dell'ordine
     *
     * @param id id dell'ordine su cui effetturare l'aggiornamento
     * @param ordineEntity entita' con gli aggiornamenti parziali da applicare
     * @return entita' aggiornata
     */
    OrdineEntity updateOrder(int id, OrdineEntity ordineEntity);


    /**
     * Lista di tutti gli ordini per una specifica comanda
     *
     * @param idComanda id della comanda su cui cercare gli ordini
     * @return lista di ordini per una data comanda
     */
    List<OrdineEntity> findAllOrdersByIdComanda(int idComanda);

    /**
     * Cancella l'ordine con il dato ID dal databse
     *
     * @param id id dell'ordine da eliminare
     */
    void deleteOrder(int id);
}
