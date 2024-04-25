package com.example.gestionecomanda.Domain.ports;

import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;

public interface CucinaPort {
    /**
     * Permette al listener in ascolto sul topic di notificare l'arrivo di una notifica di preparazione al sistema
     *
     * @param notificaPrepOrdineDTO oggetto notifica dell'invio di una notifica da parte di Gestione Cucina
     */
    void setPreparationStatus(NotificaPrepOrdineDTO notificaPrepOrdineDTO);

}
