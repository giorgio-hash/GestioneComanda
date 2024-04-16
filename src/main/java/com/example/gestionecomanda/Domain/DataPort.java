package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.ClienteEntity;

public interface DataPort {
    void getOrderData();
    void setOrderStatus();

    Iterable<ClienteEntity> getClienti();
}
