package dev.fujioka.fagnerlima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.fagnerlima.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
