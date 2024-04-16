package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.DTO.ClienteEntity;

public interface DataPort {
    void getOrderData();
    void setOrderStatus();

    Iterable<ClienteEntity> getClienti();
}
