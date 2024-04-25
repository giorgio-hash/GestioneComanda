package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;

public interface ClientePort {

    /**
     * Permette al listener in ascolto sul topic di notificare l'arrivo di un ordine al sistema
     *
     * @param notificaOrdineDTO oggetto notifica dell'invio di un ordine da parte di Gestione Cliente
     */
    void notifyOrder(NotificaOrdineDTO notificaOrdineDTO);
}
