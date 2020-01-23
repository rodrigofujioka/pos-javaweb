package dev.fujioka.fagnerlima.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.service.CrudInterface;

public abstract class BaseCrudResource<T> {

    public ResponseEntity<Page<T>> findAll(Pageable pageable) {
        return ResponseEntity.ok(getService().findAll(pageable));
    }

    public ResponseEntity<T> findById(Long id) {
        T entity = getService().findById(id).orElseThrow(() -> new EntityNotFoundException("id", id, "Informação não encontrada"));

        return ResponseEntity.ok(entity);
    }

    public ResponseEntity<T> save(T entity) {
        getService().save(entity).orElseThrow(() -> new RuntimeException());

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    public ResponseEntity<T> update(T entity) {
        getService().save(entity).orElseThrow(() -> new RuntimeException());

        return ResponseEntity.ok(entity); 
    }

    public void deleteById(Long id) {
        getService().deleteById(id);
    }

    protected abstract CrudInterface<T> getService();
}
