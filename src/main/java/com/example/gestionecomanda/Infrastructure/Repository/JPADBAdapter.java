package com.example.gestionecomanda.Infrastructure.Repository;

import com.example.gestionecomanda.Domain.DTO.ClienteDTO;
import com.example.gestionecomanda.Domain.DataPort;
import com.example.gestionecomanda.Domain.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JPADBAdapter implements DataPort {

    private final ClienteRepository clrep;

    @Autowired
    public JPADBAdapter(ClienteRepository clrep) {
        this.clrep = clrep;
    }

    @Override
    public void getOrderData() {

    }

    @Override
    public void setOrderStatus() {

    }

    @Override
    public Iterable<ClienteDTO> getClienti() {
        return clrep.findAll();
    }
}