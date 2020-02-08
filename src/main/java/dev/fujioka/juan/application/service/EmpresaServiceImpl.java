package dev.fujioka.juan.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.juan.domain.model.empresa.Empresa;
import dev.fujioka.juan.domain.service.EmpresaService;
import dev.fujioka.juan.infrastructure.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl extends BaseServiceImpl<Empresa> implements EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	@Override
	public EmpresaRepository getRepository() {
		return repository;
	}

	
}
