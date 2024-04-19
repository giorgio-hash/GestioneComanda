package com.example.gestionecomanda.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdineEntityPK implements Serializable {

    /**
     * id dell'ordine
     */
    int id;

    /**
     * id della comanda
     */
    int idComanda;

}
