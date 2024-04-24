package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends CrudRepository<OrdineEntity, Integer> {

    /**
     * Permette di ottenere una lista di Entita' Ordine con lo stesso id di comanda specificato
     *
     * @param idComanda codice identificativo della comanda
     * @return oggetto Iterable che punta a una lista contenente entita' ordine con lo stesso id di comanda specificato
     */
    Iterable<OrdineEntity> findOrdineEntitiesByIdComanda(int idComanda);

}
