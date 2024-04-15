package com.example.gestionecomanda.Domain.DTO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@jakarta.persistence.Table(name = "Ordine", schema = "serveeasy", catalog = "")
@IdClass(com.example.gestionecomanda.Domain.DTO.OrdineDTOPK.class)
public class OrdineDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false)
    private int id;

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

        OrdineDTO ordineDTO = (OrdineDTO) o;

        if (id != ordineDTO.id) return false;
        if (idComanda != ordineDTO.idComanda) return false;
        if (idPiatto != null ? !idPiatto.equals(ordineDTO.idPiatto) : ordineDTO.idPiatto != null) return false;
        if (stato != null ? !stato.equals(ordineDTO.stato) : ordineDTO.stato != null) return false;
        if (tOrdinazione != null ? !tOrdinazione.equals(ordineDTO.tOrdinazione) : ordineDTO.tOrdinazione != null)
            return false;
        if (urgenzaCliente != null ? !urgenzaCliente.equals(ordineDTO.urgenzaCliente) : ordineDTO.urgenzaCliente != null)
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
