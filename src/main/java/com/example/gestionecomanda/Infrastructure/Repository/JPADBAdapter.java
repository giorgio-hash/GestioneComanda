package com.example.gestionecomanda.Infrastructure.Repository;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import com.example.gestionecomanda.Domain.DataPort;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.Repository.ClienteRepository;
import com.example.gestionecomanda.Domain.Repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JPADBAdapter implements DataPort {

    private final ClienteRepository clrep;
    private final OrdineRepository ordineRepository;

    @Autowired
    public JPADBAdapter(ClienteRepository clrep, OrdineRepository ordineRepository) {
        this.clrep = clrep;
        this.ordineRepository = ordineRepository;
    }

    @Override
    public void getOrderData() {

    }

    @Override
    public void setOrderStatus() {

    }

    @Override
    public Iterable<ClienteEntity> getClienti() {
        return clrep.findAll();
    }

    /**
     * Salva l'entita' ordine all'interno del database
     *
     * @param ordineEntity entità ordine da salvare
     * @return entita' ordine salvata
     */
    @Override
    public OrdineEntity save(OrdineEntity ordineEntity) {
        // TODO: Salvare l'entità nel database !
        return ordineRepository.save(ordineEntity);
    }

    // TODO: Creare metodo per estrarre un ordine dal DB

}