package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends CrudRepository<OrdineEntity, Integer> {
}
