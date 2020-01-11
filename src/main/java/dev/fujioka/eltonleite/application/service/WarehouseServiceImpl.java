package dev.fujioka.eltonleite.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.eltonleite.domain.model.warehouse.Warehouse;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository.WarehouseRepository;

@Service
public class WarehouseServiceImpl implements BaseService<Warehouse> {

    @Autowired
    private WarehouseRepository repository;

    @Override
    public Warehouse save(Warehouse entity) {

        return repository.save(entity);
    }

    @Override
    public Warehouse update(Long id, Warehouse entity) {
        Warehouse savedWarehouse = findBy(id);
        savedWarehouse.setName(entity.getName());
        savedWarehouse.setAddress(entity.getAddress());
        return repository.save(savedWarehouse);
    }

    @Override
    public Warehouse findBy(Long id) {
        Optional<Warehouse> optWarehouse = repository.findById(id);
        if(!optWarehouse.isPresent()) {
            throw new RuntimeException("Deposito inexistente");
        }
        return optWarehouse.get();
    }

    @Override
    public List<Warehouse> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

}
