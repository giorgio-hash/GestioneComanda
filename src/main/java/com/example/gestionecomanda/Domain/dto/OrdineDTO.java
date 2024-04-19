package com.example.gestionecomanda.Domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdineDTO {

    private int id;

    private int idComanda;

    private String idPiatto;

    private Integer stato;

    private Timestamp tOrdinazione;

    private Integer urgenzaCliente;

}