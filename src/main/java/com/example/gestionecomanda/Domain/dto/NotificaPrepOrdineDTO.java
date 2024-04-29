package com.example.gestionecomanda.Domain.dto;

import lombok.*;

/**
 * Data Transfer Object che notifica l'avvenuta preparazione dell'ordine da parte della cucina
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NotificaPrepOrdineDTO {

    /**
     * Identificatore dell'ordine
     */
    private int id;

    /**
     * Identificatore della comanda di cui l'ordine fa parte
     */
    private int idComanda;

    /**
     * Stato dell'ordine
     * 0: Ordine preso in carico
     * 1: Ordine in coda di preparazione
     * 2: Ordine in preparazione
     * 3: Ordine preparato
     */
    private int stato;

}