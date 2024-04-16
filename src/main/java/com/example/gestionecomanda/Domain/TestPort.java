package com.example.gestionecomanda.Domain;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;

public interface TestPort {

    Iterable<ClienteDTO> getClienti();
}
