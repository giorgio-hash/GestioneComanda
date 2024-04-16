package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;

public interface TestPort {

    Iterable<ClienteEntity> getClienti();
}
