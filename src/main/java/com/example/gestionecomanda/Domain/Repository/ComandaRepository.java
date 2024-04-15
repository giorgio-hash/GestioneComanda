package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.DTO.ComandaDTO;
import org.springframework.data.repository.CrudRepository;

public interface ComandaRepository extends CrudRepository<ComandaDTO, Integer> {
}
