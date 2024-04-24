package com.example.gestionecomanda.Domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * Entità Ordine, riguarda un'ordinazione effettuata da parte del Cliente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Ordine", schema = "serveeasy", catalog = "")
public class OrdineEntity {

    /**
     * Identificatore dell'ordine
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    private int id;

    /**
     * Identificatore della comanda di cui l'ordine fa parte
     */
    @Column(name = "ID_COMANDA", nullable = false, updatable = false)
    private int idComanda;

    /**
     * Identificatore del piatto ordinato dal cliente
     */
    @Basic
    @Column(name = "ID_piatto", nullable = false, length = 20)
    private String idPiatto;

    /**
     * Stato dell'ordine
     * 0: Ordine preso in carico
     * 1: Ordine in coda di preparazione
     * 2: Ordine in preparazione
     * 3: Ordine preparato
     */
    @Basic
    @Column(name = "stato", updatable = true)
    private Integer stato;

    /**
     * Istante temporale in cui viene effettuata l'ordinazione
     * pattern : "yyyy-MM-dd HH:mm:ss.SSS"
     */
    @Basic
    @CreationTimestamp
    @Column(name = "t_ordinazione", updatable = false)
    private Timestamp tOrdinazione;

    /**
     * Attributo urgenza del cliente
     * 0 : espresso non urgenza
     * 1 : espresso urgenza
     * 2 : default
     */
    @Basic
    @Column(name = "urgenza_cliente", updatable = true)
    private Integer urgenzaCliente;

}