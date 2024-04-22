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
@Table(name = "Ordine", schema = "serveeasy", catalog = "")
public class OrdineEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    //@Basic
    @Column(name = "ID_comanda", nullable = false)
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