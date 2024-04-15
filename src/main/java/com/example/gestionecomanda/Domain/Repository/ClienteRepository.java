package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<ClienteDTO, String> {
}
