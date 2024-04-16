package com.example.gestionecomanda.Domain.mappers.impl;

import com.example.gestionecomanda.Domain.dto.OrdineDTO;
import com.example.gestionecomanda.Domain.Entity.Ordine;
import com.example.gestionecomanda.Domain.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineMapper implements Mapper<Ordine, OrdineDTO> {

    private ModelMapper modelMapper;

    public OrdineMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrdineDTO mapTo(Ordine ordine) {
        return modelMapper.map(ordine,OrdineDTO.class);
    }

    @Override
    public Ordine mapFrom(OrdineDTO ordineDTO) {
        return modelMapper.map(ordineDTO, Ordine.class);
    }
}
