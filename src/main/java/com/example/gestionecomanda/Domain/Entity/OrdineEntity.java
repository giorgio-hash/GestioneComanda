package com.example.gestionecomanda.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@jakarta.persistence.Table(name = "Ordine", schema = "serveeasy", catalog = "")
@IdClass(com.example.gestionecomanda.Domain.Entity.OrdineEntityPK.class)
public class OrdineEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false)
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID_comanda", nullable = false)
    private int idComanda;
    
    @Basic
    @Column(name = "ID_piatto", nullable = false, length = 20)
    private String idPiatto;

    @Basic
    @Column(name = "stato", nullable = true)
    private Integer stato;
    
    @Basic
    @Column(name = "t_ordinazione", nullable = true)
    private Timestamp tOrdinazione;

    @Basic
    @Column(name = "urgenza_cliente", nullable = true)
    private Integer urgenzaCliente;

}