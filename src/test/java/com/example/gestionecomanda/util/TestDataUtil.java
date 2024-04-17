package com.example.gestionecomanda.util;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;

public class TestDataUtil {

    public static OrdineDTO createOrdineDtoA(){
        return OrdineDTO.builder()
                .id(22)
                .idComanda(7)
                .build();
    }

}
