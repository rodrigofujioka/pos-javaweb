package dev.fujioka.juan.infrastructure.repository;

import java.util.List;

import dev.fujioka.juan.domain.model.produto.Produto;

public interface ProdutoRepository extends BaseRepository<Produto, Long> {

	public List<Produto> findByAnoFabricacao(int anoFabricacao);

}
