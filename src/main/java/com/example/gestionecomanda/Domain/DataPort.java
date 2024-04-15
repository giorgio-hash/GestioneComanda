package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;

public interface DataPort {
    void getOrderData();
    void setOrderStatus();

    Iterable<ClienteDTO> getClienti();
}
