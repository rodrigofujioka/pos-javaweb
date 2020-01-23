package dev.fujioka.fagnerlima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.repository.ClientRepository;

@Service
public class ClientService extends BaseCrudService<Client> {

    @Autowired
    private ClientRepository clienteRepository;

    @Override
    protected JpaRepository<Client, Long> getRepository() {
        return clienteRepository;
    }

}
