package dev.fujioka.fagnerlima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.fagnerlima.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
