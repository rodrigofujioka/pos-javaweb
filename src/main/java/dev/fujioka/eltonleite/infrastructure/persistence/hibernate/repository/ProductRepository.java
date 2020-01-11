package dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.eltonleite.domain.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    public List<Product> findByManufactureYearBetween(Integer startManufactureYear, Integer endManufactureYear);
    
    public List<Product> findByManufactureYear(Integer manufactureYear);
    
}
