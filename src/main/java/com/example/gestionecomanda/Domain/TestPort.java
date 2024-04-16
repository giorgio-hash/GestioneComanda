package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.DTO.ClienteEntity;

public interface TestPort {

    Iterable<ClienteEntity> getClienti();
}
