package com.example.gestionecomanda.Infrastructure.Repository;

import com.example.gestionecomanda.Domain.ports.DataPort;
import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.Repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Classe adatatore della porta DataPort con la quale si effettuano operazioni sul database utilizzando JPA
 */
@Repository
public class JPADBAdapter implements DataPort {

    private final OrdineRepository ordineRepository;

    @Autowired
    public JPADBAdapter(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    /* ORDINE */

    @Override
    public boolean isOrderExist(int id) {
        return ordineRepository.existsById(id);
    }

    @Override
    public OrdineEntity saveOrder(OrdineEntity ordineEntity) {
        return ordineRepository.save(ordineEntity);
    }

    @Override
    public Optional<OrdineEntity> getOrderById(int id) {
        return ordineRepository.findById(id);
    }

    @Override
    public OrdineEntity updateOrder(int id, OrdineEntity ordineEntity) {
        ordineEntity.setId(id);

        return ordineRepository.findById(ordineEntity.getId()).map(existingOrder -> {
            Optional.ofNullable(ordineEntity.getStato()).ifPresent(existingOrder::setStato);
            Optional.ofNullable(ordineEntity.getUrgenzaCliente()).ifPresent(existingOrder::setUrgenzaCliente);
            return ordineRepository.save(existingOrder);
        }).orElseThrow(() -> new RuntimeException("Order does not exist"));
    }

    @Override
    public List<OrdineEntity> findAllOrdersByIdComanda(int idComanda) {
        return StreamSupport.stream(ordineRepository
                                .findOrdineEntitiesByIdComanda(idComanda)
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(int id) {
        ordineRepository.deleteById(id);
    }

}