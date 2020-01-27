package dev.fujioka.juan.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fujioka.juan.domain.model.produto.Produto;
import dev.fujioka.juan.domain.service.ProdutoService;
import dev.fujioka.juan.infrastructure.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl extends BaseServiceImpl<Produto> implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Override
	public ProdutoRepository getRepository() {
		return repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> listarPorAnoFabricao(int anoFabricacao) {
		return repository.findByAnoFabricacao(anoFabricacao);
	}

	
}
