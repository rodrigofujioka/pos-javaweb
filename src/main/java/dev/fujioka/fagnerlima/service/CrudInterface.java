package dev.fujioka.fagnerlima.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudInterface<T>  {

    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    Optional<T> save(T entity);
    Optional<T> findById(long id);
    void delete(T entity);
    void deleteById(long id);
    long count();


}
