package dev.fujioka.eltonleite.domain.service;

import java.util.List;

import dev.fujioka.eltonleite.domain.model.product.Product;

public interface ProductService extends BaseService<Product> {

    
    public List<Product> findByManufactureYear(Integer year);

    List<Product> findByManufactureYearBetween(Integer startYear, Integer endYear);
    
    
}
