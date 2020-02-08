package dev.fujioka.juan.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.juan.domain.model.lancamento.Lancamento;
import dev.fujioka.juan.domain.service.LancamentoService;
import dev.fujioka.juan.infrastructure.repository.LancamentoRepository;

@Service
public class LancamentoServiceImpl extends BaseServiceImpl<Lancamento> implements LancamentoService {

	@Autowired
	private LancamentoRepository repository;

	@Override
	public LancamentoRepository getRepository() {
		return repository;
	}
}
