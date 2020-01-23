package dev.fujioka.fagnerlima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.fagnerlima.domain.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

}
