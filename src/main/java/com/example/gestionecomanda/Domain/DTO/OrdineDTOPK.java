package com.example.gestionecomanda.Domain.DTO;

import java.io.Serializable;

public class OrdineDTOPK implements Serializable {

    private int id;
    private int idComanda;

    public OrdineDTOPK(int id, int idComanda) {
        this.id = id;
        this.idComanda = idComanda;
    }


}
