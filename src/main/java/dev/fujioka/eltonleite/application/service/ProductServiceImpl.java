package dev.fujioka.eltonleite.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.eltonleite.domain.model.product.Product;
import dev.fujioka.eltonleite.domain.service.ProductService;
import dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product entity) {

        return repository.save(entity);
    }

    @Override
    public Product update(Long id, Product entity) {
        Product savedProduct = findBy(id);
        savedProduct.setName(entity.getName());
        savedProduct.setDescription(entity.getDescription());
        savedProduct.setManufactureYear(entity.getManufactureYear());
        return repository.save(savedProduct);
    }

    @Override
    public Product findBy(Long id) {
        Optional<Product> optProduct = repository.findById(id);
        if(!optProduct.isPresent()) {
            throw new RuntimeException("Produto inexistente");
        }
        return optProduct.get();
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
    
    @Override
    public List<Product> findByManufactureYear(Integer manufactureYear) {
        return repository.findByManufactureYear(manufactureYear);
    }
    
    @Override
    public List<Product> findByManufactureYearBetween(Integer startYear, Integer endYear) {
        return repository.findByManufactureYearBetween(startYear, endYear);
    }
    
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

}
