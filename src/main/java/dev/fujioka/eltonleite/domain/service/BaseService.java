package dev.fujioka.eltonleite.domain.service;

import java.util.List;

public interface BaseService<T> {

    public T save(T entity);
    
    public T update(Long id, T entity);
    
    public T findBy(Long id);
    
    public List<T> findAll();
    
    public void delete(Long id);
    
}
