package dev.fujioka.juan.application.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fujioka.juan.application.service.exception.InformacaoNaoEncontradaException;
import dev.fujioka.juan.domain.service.BaseService;
import dev.fujioka.juan.infrastructure.repository.BaseRepository;
import dev.fujioka.juan.infrastructure.util.BeanUtils;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Override
	@Transactional
	public T salvar(T entidade) {
		return getRepository().save(entidade);
	}

	@Override
	@Transactional(readOnly = true)
	public T buscar(Long id) {
		Optional<T> usuario = getRepository().findById(id);
		return usuario.orElseThrow(() -> new InformacaoNaoEncontradaException());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<T> listarTodos(Pageable page) {
		return getRepository().findAll(page);

	}

	@Override
	@Transactional
	public T atualizar(Long id, T entidade) {
		T entidadeSalva = buscar(id);
		BeanUtils.copyProperties(entidade, entidadeSalva);
		return salvar(entidadeSalva);
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		T entidadeSalva = buscar(id);
		getRepository().delete(entidadeSalva);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<T> listarTodos(Specification<T> predicate, Pageable page) {
		return getRepository().findAll(predicate, page);
	}

	public abstract BaseRepository<T, Long> getRepository();
}
