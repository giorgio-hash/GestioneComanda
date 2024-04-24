package com.example.gestionecomanda.Domain.dto;

import lombok.*;

/**
 * Data Transfer Object che notifica un aggiornamento della base dati Ordine
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NotificaOrdineDTO {

    /**
     * Identificatore dell'ordine
     */
    private int id;

    /**
     * Identificatore della comanda di cui l'ordine fa parte
     */
    private int idComanda;

}