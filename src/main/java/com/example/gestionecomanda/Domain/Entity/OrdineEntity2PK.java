package com.example.gestionecomanda.Domain.Entity;

import java.io.Serializable;
import java.util.Objects;

public class OrdineEntity2PK implements Serializable {

    int id;
    int idcomanda;

    public OrdineEntity2PK(int id, int idcomanda) {
        this.id = id;
        this.idcomanda = idcomanda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrdineEntity2PK that)) return false;
        return getId() == that.getId() && getIdcomanda() == that.getIdcomanda();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdcomanda());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcomanda() {
        return idcomanda;
    }

    public void setIdcomanda(int idcomanda) {
        this.idcomanda = idcomanda;
    }
}
