package dev.fujioka.fagnerlima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.repository.StoreRepository;

@Service
public class StoreService extends BaseCrudService<Store> {

    @Autowired
    private StoreRepository lojaRepository;

    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return lojaRepository;
    }

}
