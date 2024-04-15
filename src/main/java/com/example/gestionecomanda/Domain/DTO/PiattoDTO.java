package com.example.gestionecomanda.Domain.DTO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Piatto", schema = "serveeasy", catalog = "")
public class PiattoDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 20)
    private String id;
    @Basic
    @Column(name = "ID_ingr_princ", nullable = false, length = 20)
    private String idIngrPrinc;
    @Basic
    @Column(name = "descrizione", nullable = true, length = 50)
    private String descrizione;
    @Basic
    @Column(name = "prezzo", nullable = false, precision = 0)
    private double prezzo;
    @Basic
    @Column(name = "t_preparazione", nullable = true)
    private Timestamp tPreparazione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdIngrPrinc() {
        return idIngrPrinc;
    }

    public void setIdIngrPrinc(String idIngrPrinc) {
        this.idIngrPrinc = idIngrPrinc;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Timestamp gettPreparazione() {
        return tPreparazione;
    }

    public void settPreparazione(Timestamp tPreparazione) {
        this.tPreparazione = tPreparazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PiattoDTO piattoDTO = (PiattoDTO) o;

        if (Double.compare(prezzo, piattoDTO.prezzo) != 0) return false;
        if (id != null ? !id.equals(piattoDTO.id) : piattoDTO.id != null) return false;
        if (idIngrPrinc != null ? !idIngrPrinc.equals(piattoDTO.idIngrPrinc) : piattoDTO.idIngrPrinc != null)
            return false;
        if (descrizione != null ? !descrizione.equals(piattoDTO.descrizione) : piattoDTO.descrizione != null)
            return false;
        if (tPreparazione != null ? !tPreparazione.equals(piattoDTO.tPreparazione) : piattoDTO.tPreparazione != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idIngrPrinc != null ? idIngrPrinc.hashCode() : 0);
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        temp = Double.doubleToLongBits(prezzo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tPreparazione != null ? tPreparazione.hashCode() : 0);
        return result;
    }
}
