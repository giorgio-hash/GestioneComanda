package com.example.gestionecomanda.Domain.Repository;

import com.example.gestionecomanda.Domain.DTO.OrdineDTO;
import com.example.gestionecomanda.Domain.DTO.OrdineDTOPK;
import org.springframework.data.repository.CrudRepository;

public interface OrdineRepository extends CrudRepository<OrdineDTO, OrdineDTOPK> {
}
