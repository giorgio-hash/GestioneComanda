package com.example.gestionecomanda.util;

import com.example.gestionecomanda.Domain.Entity.OrdineEntity;
import com.example.gestionecomanda.Domain.dto.NotificaOrdineDTO;
import com.example.gestionecomanda.Domain.dto.NotificaPrepOrdineDTO;
import com.example.gestionecomanda.Domain.dto.OrdineDTO;

/**
 * Oggetti utili nei test
 */
public class TestDataUtil {

    // ATTENZIONE: non modificare i valori !

    public static OrdineEntity createOrdineEntityA(){
        return OrdineEntity.builder()
                .idComanda(7)
                .idPiatto("RIS188")
                .stato(1)
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineDTO createOrdineDtoA(){
        return OrdineDTO.builder()
                .idComanda(7)
                .idPiatto("RIS188")
                .stato(1)
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineEntity createOrdineEntityB(){
        return OrdineEntity.builder()
                .id(16)
                .idComanda(7)
                .idPiatto("SPA279")
                .stato(1)
                .urgenzaCliente(1)
                .build();
    }

    public static OrdineDTO createOrdineDtoB(){
        return OrdineDTO.builder()
                .id(16)
                .idComanda(7)
                .idPiatto("SPA279")
                .stato(1)
                .urgenzaCliente(1)
                .build();
    }

    public static OrdineEntity createOrdineEntityC(){
        return OrdineEntity.builder()
                .id(55)
                .idComanda(37)
                .idPiatto("PES789")
                .stato(0)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }

    public static OrdineDTO createOrdineDtoC(){
        return OrdineDTO.builder()
                .id(55)
                .idComanda(37)
                .idPiatto("PES789")
                .stato(0)
                .tOrdinazione(new java.sql.Timestamp(System.currentTimeMillis()))
                .urgenzaCliente(0)
                .build();
    }

    public static NotificaOrdineDTO createNotificaOrdineDTOA(){
        return NotificaOrdineDTO.builder()
                .id(1)
                .idComanda(7)
                .build();
    }

    public static NotificaOrdineDTO createNotificaOrdineDTOB(){
        return NotificaOrdineDTO.builder()
                .id(2)
                .idComanda(4)
                .build();
    }

    public static NotificaPrepOrdineDTO createotificaPrepOrdineDTOA(){
        return NotificaPrepOrdineDTO.builder()
                .id(1)
                .idComanda(7)
                .build();
    }

    public static NotificaPrepOrdineDTO createotificaPrepOrdineDTOB(){
        return NotificaPrepOrdineDTO.builder()
                .id(2)
                .idComanda(4)
                .build();
    }


}
