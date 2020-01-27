package dev.fujioka.juan.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.juan.domain.model.produto.Produto;
import dev.fujioka.juan.domain.service.ProdutoService;
import dev.fujioka.juan.infrastructure.repository.ProdutoRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@AfterEach
	public void tearDown() {
		produtoRepository.deleteAll();
	}

	@Test
	public void testEncontrarTodosProdutos() {
		gerarProdutosESalvar(5);
		List<Produto> produtos = produtoRepository.findAll();
		assertThat(produtos.size()).isEqualTo(5);
	}

	@Test
	public void testSalvar() {
		Produto produto = gerarProduto();

		Optional.of(produtoService.salvar(produto)).orElseThrow();

		assertThat(produto.getId()).isNotNull();
	}

	@Test
	public void testEncontrarPorId() {
		Produto produtoMocado = produtoService.salvar(gerarProduto());
		Produto produtoEncontrado = produtoService.buscar(produtoMocado.getId());
		assertThat(produtoEncontrado.getId()).isEqualTo(produtoMocado.getId());
	}

	private List<Produto> gerarProdutosESalvar(Integer amount) {
		return produtoRepository.saveAll(gerarProdutos(amount));
	}

	private List<Produto> gerarProdutos(Integer quantidade) {
		List<Produto> produtos = new ArrayList<>();

		for (int i = 0; i < quantidade; i++) {
			Produto produto = new Produto();
			produto.setNome("produto" + i);
			produto.setDescricao("descricao" + i);
			produto.setAnoFabricacao(i);
			produtos.add(produto);
		}

		return produtos;
	}

	private Produto gerarProduto() {
		Produto produto = new Produto();
		produto.setNome("produto");
		produto.setDescricao("descricao");
		produto.setAnoFabricacao(2020);

		return produto;
	}

}
