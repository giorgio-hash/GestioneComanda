package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;

public interface AlgIF {
    void pushNewOrder(OrdineEntity ordineEntity);
    void setOrderStatus(NotificaOrdineDTO notificaOrdineDTO);
}
