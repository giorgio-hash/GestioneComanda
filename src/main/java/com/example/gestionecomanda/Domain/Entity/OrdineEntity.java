package com.example.gestionecomanda.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private int id;

    @Column(name = "ID_COMANDA", nullable = false, updatable = false)
    private int idComanda;
    
    @Basic
    @Column(name = "ID_piatto", nullable = false, length = 20)
    private String idPiatto;

    @Basic
    @Column(name = "stato", updatable = true)
    private Integer stato;
    
    @Basic
    @CreationTimestamp
    @Column(name = "t_ordinazione", updatable = false)
    private Timestamp tOrdinazione;

    @Basic
    @Column(name = "urgenza_cliente", updatable = true)
    private Integer urgenzaCliente;

}