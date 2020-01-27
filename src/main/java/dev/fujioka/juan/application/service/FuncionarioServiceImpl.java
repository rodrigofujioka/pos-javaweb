package dev.fujioka.juan.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fujioka.juan.application.service.exception.EmpresaNaoEncontradaException;
import dev.fujioka.juan.domain.model.empresa.Empresa;
import dev.fujioka.juan.domain.model.funcionario.Funcionario;
import dev.fujioka.juan.domain.service.FuncionarioService;
import dev.fujioka.juan.infrastructure.repository.EmpresaRepository;
import dev.fujioka.juan.infrastructure.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl extends BaseServiceImpl<Funcionario> implements FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public FuncionarioRepository getRepository() {
		return repository;
	}

	@Override
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		analisarFuncionario(funcionario);
		return getRepository().save(funcionario);
	}

	private void analisarFuncionario(Funcionario funcionario) {
		if (funcionario.getEmpresa() != null) {
			empresaRepository.findById(funcionario.getEmpresa().getId())
					.orElseThrow(() -> new EmpresaNaoEncontradaException());
		}
	}

}
