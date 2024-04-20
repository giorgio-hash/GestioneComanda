package com.example.gestionecomanda.Domain.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@jakarta.persistence.Table(name = "Cliente", schema = "serveeasy", catalog = "")
public class ClienteEntity {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false, length = 10)
    private String id;

    @Basic
    @Column(name = "t_o_a", nullable = false)
    private byte tOA;

}
