package com.example.gestionecomanda.Domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * DTO della classe OrdineEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdineDTO {

    /**
     * Identificatore dell'ordine
     */
    private int id;

    /**
     * Identificatore della comanda di cui l'ordine fa parte
     */
    private int idComanda;

    /**
     * Identificatore del piatto ordinato dal cliente
     */
    private String idPiatto;

    /**
     * Stato dell'ordine
     * 0: Ordine preso in carico
     * 1: Ordine in coda di preparazione
     * 2: Ordine in preparazione
     * 3: Ordine preparato
     */
    private Integer stato;

    /**
     * Istante temporale in cui viene effettuata l'ordinazione
     * pattern : "yyyy-MM-dd HH:mm:ss.SSS"
     */
    private Timestamp tOrdinazione;

    /**
     * Attributo urgenza del cliente
     * 0 : espresso non urgenza
     * 1 : espresso urgenza
     * 2 : default
     */
    private Integer urgenzaCliente;

}