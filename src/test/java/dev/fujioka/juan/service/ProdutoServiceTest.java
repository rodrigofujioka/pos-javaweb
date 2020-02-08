package dev.fujioka.juan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.juan.application.service.exception.InformacaoNaoEncontradaException;
import dev.fujioka.juan.domain.model.produto.Produto;
import dev.fujioka.juan.domain.service.ProdutoService;
import dev.fujioka.juan.infrastructure.repository.ProdutoRepository;
import dev.fujioka.juan.infrastructure.util.BeanUtils;

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
	public void testEncontrarTodosProdutosComPaginacao() {
		gerarProdutosESalvar(20);
		Page<Produto> retorno = produtoService.listarTodos(PageRequest.of(0, 10));
		assertThat(retorno.getContent().size()).isEqualTo(10);
		assertThat(retorno.getNumberOfElements()).isEqualTo(10);
		assertThat(retorno.getTotalElements()).isEqualTo(20);
		assertThat(retorno.getTotalPages()).isEqualTo(2);
		assertThat(retorno.getSize()).isEqualTo(10);
		assertThat(retorno.getNumber()).isEqualTo(0);

	}

	@Test
	public void testSalvar() {
		Produto produto = gerarProduto();

		Optional.of(produtoService.salvar(produto)).orElseThrow();

		assertThat(produto.getId()).isNotNull();
	}

	@Test
	public void testAlterar() {
		Produto produtoMocado = gerarProdutoESalvar();

		Produto produtoAlterado = new Produto();
		BeanUtils.copyProperties(produtoMocado, produtoAlterado);
		produtoAlterado.setNome("Produto alterado");
		produtoAlterado.setAnoFabricacao(2089);
		produtoService.salvar(produtoAlterado);

		assertThat(produtoAlterado.getId()).isEqualTo(produtoMocado.getId());
		assertThat(produtoAlterado.getNome()).isNotEqualTo(produtoMocado.getNome());
		assertThat(produtoAlterado.getDescricao()).isEqualTo(produtoMocado.getDescricao());
		assertThat(produtoAlterado.getAnoFabricacao()).isNotEqualTo(produtoMocado.getAnoFabricacao());

	}

	@Test
	public void testEncontrarPorId() {
		Produto produtoMocado = produtoService.salvar(gerarProduto());
		Produto produtoEncontrado = produtoService.buscar(produtoMocado.getId());
		assertThat(produtoEncontrado.getId()).isEqualTo(produtoMocado.getId());
	}

	@Test
	public void testEncontrarPorIdNaoEncontrado() {
		assertThrows(InformacaoNaoEncontradaException.class, () -> produtoService.buscar(950L));
	}

	@Test
	public void testDeletar() {
		Produto produtoMocado = gerarProdutoESalvar();
		assertThatCode(() -> produtoService.deletar(produtoMocado.getId())).doesNotThrowAnyException();
		;
	}

	private Produto gerarProdutoESalvar() {
		return produtoService.salvar(gerarProduto());
	}

	private Produto gerarProduto() {
		Produto produto = new Produto();
		produto.setNome("produto");
		produto.setDescricao("descricao");
		produto.setAnoFabricacao(2020);

		return produto;
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

}
