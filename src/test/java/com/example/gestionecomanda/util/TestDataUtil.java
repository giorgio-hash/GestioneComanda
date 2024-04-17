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
                .build();
    }

    public static OrdineDTO createOrdineDtoA(){
        return OrdineDTO.builder()
                .id(22)
                .idComanda(7)
                .build();
    }

    public static OrdineEntity createOrdineEntityB(){
        return OrdineEntity.builder()
                .id(16)
                .idComanda(1)
                .build();
    }

    public static OrdineDTO createOrdineDtoB(){
        return OrdineDTO.builder()
                .id(16)
                .idComanda(1)
                .build();
    }

    public static OrdineEntity createOrdineEntityC(){
        return OrdineEntity.builder()
                .id(99)
                .idComanda(37)
                .build();
    }

    public static OrdineDTO createOrdineDtoC(){
        return OrdineDTO.builder()
                .id(99)
                .idComanda(37)
                .build();
    }


}
