package dev.fujioka.juan.domain.service;

import java.util.List;

import dev.fujioka.juan.domain.model.produto.Produto;

public interface ProdutoService extends BaseService<Produto> {

	public List<Produto> listarPorAnoFabricao(int anoFabricacao);
}
