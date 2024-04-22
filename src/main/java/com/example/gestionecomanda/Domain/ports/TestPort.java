package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;

public interface TestPort {

    Iterable<ClienteEntity> getClienti();
}
