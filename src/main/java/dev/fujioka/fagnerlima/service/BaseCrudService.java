package dev.fujioka.fagnerlima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseCrudService<T> implements CrudInterface<T> {

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Optional<T> save(T entity) {
        return Optional.of(getRepository().save(entity));
    }

    @Override
    public Optional<T> findById(long id) {
        return getRepository().findById(id);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(long id) {
        getRepository().deleteById(id);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    protected abstract JpaRepository<T, Long> getRepository();
}
