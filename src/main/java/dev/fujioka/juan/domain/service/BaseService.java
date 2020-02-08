package dev.fujioka.juan.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<T> {

	public T salvar(T entidade);

	public T buscar(Long id);

	public Page<T> listarTodos(Pageable page);

	public Page<T> listarTodos(Specification<T> predicate, Pageable page);

	public T atualizar(Long id, T entidade);

	public void deletar(Long id);

}
