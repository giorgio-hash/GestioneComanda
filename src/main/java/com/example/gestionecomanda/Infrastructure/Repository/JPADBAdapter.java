package com.example.gestionecomanda.Infrastructure.Repository;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import com.example.gestionecomanda.Domain.ports.DataPort;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.Repository.ClienteRepository;
import com.example.gestionecomanda.Domain.Repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JPADBAdapter implements DataPort {

    private final ClienteRepository clrep;
    private final OrdineRepository ordineRepository;

    @Autowired
    public JPADBAdapter(ClienteRepository clrep, OrdineRepository ordineRepository) {
        this.clrep = clrep;
        this.ordineRepository = ordineRepository;
    }

    /* ORDINE */

    @Override
    public OrdineEntity saveOrder(OrdineEntity ordineEntity) {
        return ordineRepository.save(ordineEntity);
    }

    @Override
    public Optional<OrdineEntity> findOrderById(int id) {
        return ordineRepository.findById(id);
    }

    @Override
    public void getOrderData() {

    }

    @Override
    public void setOrderStatus() {

    }

    /* CLIENTE */

    @Override
    public Iterable<ClienteEntity> getClienti() {
        return clrep.findAll();
    }

}