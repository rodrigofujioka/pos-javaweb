package dev.fujioka.fagnerlima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.fujioka.fagnerlima.domain.Product;
import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.repository.ProductRepository;

@Service
public class ProductService
        implements CrudInterface<Product> {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> save(Product entity) {

        return Optional.of(productRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(long id) {

        return productRepository.findById(id);

    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    public Optional<Product> addAmount(Long id, Integer amount) {
        Optional<Product> entityOpt = findById(id);
        Product entity = entityOpt.orElseThrow(() -> new EntityNotFoundException("id", id, "Product not found"));
        entity.addAmount(amount);

        return save(entity);
    }

}