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

}