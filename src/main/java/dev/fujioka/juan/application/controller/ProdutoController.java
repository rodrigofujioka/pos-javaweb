package dev.fujioka.juan.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.juan.domain.model.produto.Produto;
import dev.fujioka.juan.domain.service.ProdutoService;
import dev.fujioka.juan.infrastructure.service.ConverterServiceImpl;
import dev.fujioka.juan.infrastructure.service.ResponseServiceImpl;
import dev.fujioka.juan.infrastructure.specification.SpecificationBuilder;
import dev.fujioka.juan.presentation.dto.ResponseTO;
import dev.fujioka.juan.presentation.dto.produto.BuscaRequestTO;
import dev.fujioka.juan.presentation.dto.produto.ProdutoRequestTO;
import dev.fujioka.juan.presentation.dto.produto.ProdutoResponseTO;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ConverterServiceImpl converterService;
	
	@Autowired
	private SpecificationBuilder specificationBuilder;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ResponseServiceImpl responseService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTO<ProdutoResponseTO>> buscar(@PathVariable Long id) {
		Produto produto = produtoService.buscar(id);
		ProdutoResponseTO produtoResponseTO = converterService.converter(produto, ProdutoResponseTO.class);
		return responseService.ok(produtoResponseTO);
	}

	@GetMapping
	public ResponseEntity<ResponseTO<Page<ProdutoResponseTO>>> listar(BuscaRequestTO buscaoTO, Pageable pagina) {
		Specification<Produto> predicate = specificationBuilder.criarFiltro(buscaoTO, Produto.class);
		Page<Produto> produtos = produtoService.listarTodos(predicate,pagina);
		Page<ProdutoResponseTO> produtosResponseTO = converterService.converter(produtos, ProdutoResponseTO.class);
		return responseService.ok(produtosResponseTO);

	}

	@GetMapping(params = "anoFabricacao")
	public ResponseEntity<ResponseTO<List<ProdutoResponseTO>>> listarPorAnoFabricao(@RequestParam int anoFabricacao) {
		List<Produto> produtos = produtoService.listarPorAnoFabricao(anoFabricacao);
		List<ProdutoResponseTO> produtoResponseTO = converterService.convert(produtos, ProdutoResponseTO.class);
		return responseService.ok(produtoResponseTO);
	}

	@PostMapping
	public ResponseEntity<ResponseTO<ProdutoResponseTO>> salvar(@RequestBody @Valid ProdutoRequestTO produtoRequestTO) {
		Produto produto = converterService.converter(produtoRequestTO, Produto.class);
		Produto produtoSalvo = produtoService.salvar(produto);
		ProdutoResponseTO produtoResponseTO = converterService.converter(produtoSalvo, ProdutoResponseTO.class);
		return responseService.create(produtoResponseTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseTO<ProdutoResponseTO>> alterar(@PathVariable Long id,
			@RequestBody @Valid ProdutoRequestTO produtoRequestTO) {
		Produto produto = converterService.converter(produtoRequestTO, Produto.class);
		Produto produtoAtualizado = produtoService.atualizar(id, produto);
		ProdutoResponseTO produtoResponseTO = converterService.converter(produtoAtualizado, ProdutoResponseTO.class);
		return responseService.ok(produtoResponseTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		produtoService.deletar(id);
	}
}
