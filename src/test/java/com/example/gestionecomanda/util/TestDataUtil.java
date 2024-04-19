package com.example.gestionecomanda.util;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;

/**
 * Oggetti utili nei test
 */
public class TestDataUtil {

    public static OrdineEntity createOrdineEntityA(){
        return OrdineEntity.builder()
                .id(22)
                .idComanda(7)
                .idPiatto("RIS188")
                .stato(1)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineDTO createOrdineDtoA(){
        return OrdineDTO.builder()
                .id(22)
                .idComanda(7)
                .idPiatto("RIS188")
                .stato(1)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineEntity createOrdineEntityB(){
        return OrdineEntity.builder()
                .id(16)
                .idComanda(1)
                .idPiatto("SPA279")
                .stato(1)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(1)
                .build();
    }

    public static OrdineDTO createOrdineDtoB(){
        return OrdineDTO.builder()
                .id(16)
                .idComanda(1)
                .idPiatto("SPA279")
                .stato(1)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(1)
                .build();
    }

    public static OrdineEntity createOrdineEntityC(){
        return OrdineEntity.builder()
                .id(99)
                .idComanda(37)
                .idPiatto("PES789")
                .stato(0)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineDTO createOrdineDtoC(){
        return OrdineDTO.builder()
                .id(99)
                .idComanda(37)
                .idPiatto("PES789")
                .stato(0)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }


}
