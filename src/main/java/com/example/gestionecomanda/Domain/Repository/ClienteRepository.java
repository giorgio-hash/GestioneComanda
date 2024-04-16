package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<ClienteEntity, String> {
}
