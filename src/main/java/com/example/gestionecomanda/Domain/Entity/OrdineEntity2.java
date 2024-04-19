package com.example.gestionecomanda.Domain.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@jakarta.persistence.Table(name = "Ordine", schema = "serveeasy", catalog = "")
@IdClass(com.example.gestionecomanda.Domain.Entity.OrdineEntity2PK.class)
public class OrdineEntity2 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false)
    private int id;

    public OrdineEntity2(int id, int idComanda, String idPiatto, Integer stato, Timestamp tOrdinazione, Integer urgenzaCliente) {
        this.id = id;
        this.idComanda = idComanda;
        this.idPiatto = idPiatto;
        this.stato = stato;
        this.tOrdinazione = tOrdinazione;
        this.urgenzaCliente = urgenzaCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID_comanda", nullable = false)
    private int idComanda;

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    @Basic
    @Column(name = "ID_piatto", nullable = false, length = 20)
    private String idPiatto;

    public String getIdPiatto() {
        return idPiatto;
    }

    public void setIdPiatto(String idPiatto) {
        this.idPiatto = idPiatto;
    }

    @Basic
    @Column(name = "stato", nullable = true)
    private Integer stato;

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
    }

    @Basic
    @Column(name = "t_ordinazione", nullable = true)
    private Timestamp tOrdinazione;

    public Timestamp gettOrdinazione() {
        return tOrdinazione;
    }

    public void settOrdinazione(Timestamp tOrdinazione) {
        this.tOrdinazione = tOrdinazione;
    }

    @Basic
    @Column(name = "urgenza_cliente", nullable = true)
    private Integer urgenzaCliente;

    public Integer getUrgenzaCliente() {
        return urgenzaCliente;
    }

    public void setUrgenzaCliente(Integer urgenzaCliente) {
        this.urgenzaCliente = urgenzaCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdineEntity2 that = (OrdineEntity2) o;

        if (id != that.id) return false;
        if (idComanda != that.idComanda) return false;
        if (idPiatto != null ? !idPiatto.equals(that.idPiatto) : that.idPiatto != null) return false;
        if (stato != null ? !stato.equals(that.stato) : that.stato != null) return false;
        if (tOrdinazione != null ? !tOrdinazione.equals(that.tOrdinazione) : that.tOrdinazione != null) return false;
        if (urgenzaCliente != null ? !urgenzaCliente.equals(that.urgenzaCliente) : that.urgenzaCliente != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idComanda;
        result = 31 * result + (idPiatto != null ? idPiatto.hashCode() : 0);
        result = 31 * result + (stato != null ? stato.hashCode() : 0);
        result = 31 * result + (tOrdinazione != null ? tOrdinazione.hashCode() : 0);
        result = 31 * result + (urgenzaCliente != null ? urgenzaCliente.hashCode() : 0);
        return result;
    }
}
