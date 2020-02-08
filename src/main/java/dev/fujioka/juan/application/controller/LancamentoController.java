package dev.fujioka.juan.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.juan.domain.model.lancamento.Lancamento;
import dev.fujioka.juan.domain.service.LancamentoService;
import dev.fujioka.juan.infrastructure.service.ConverterServiceImpl;
import dev.fujioka.juan.infrastructure.service.ResponseServiceImpl;
import dev.fujioka.juan.presentation.dto.ResponseTO;
import dev.fujioka.juan.presentation.dto.lancamento.LancamentoRequestTO;
import dev.fujioka.juan.presentation.dto.lancamento.LancamentoResponseTO;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

	@Autowired
	private ConverterServiceImpl converterService;

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ResponseServiceImpl responseService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTO<LancamentoResponseTO>> buscar(@PathVariable Long id) {
		Lancamento lancamento = lancamentoService.buscar(id);
		LancamentoResponseTO lancamentoResponseTO = converterService.converter(lancamento, LancamentoResponseTO.class);
		return responseService.ok(lancamentoResponseTO);

	}

	@PostMapping
	public ResponseEntity<ResponseTO<LancamentoResponseTO>> salvar(@RequestBody @Valid LancamentoRequestTO lancamentoRequestTO) {
		Lancamento lancamento = converterService.converter(lancamentoRequestTO, Lancamento.class);
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		LancamentoResponseTO lancamentoResponseTO = converterService.converter(lancamentoSalvo,
				LancamentoResponseTO.class);
		return responseService.create(lancamentoResponseTO);

	}

	@GetMapping
	public ResponseEntity<ResponseTO<Page<LancamentoResponseTO>>> listar(Pageable pagina) {
		Page<Lancamento> lancamentos = lancamentoService.listarTodos(pagina);
		Page<LancamentoResponseTO> lancamentosResponseTO = converterService.converter(lancamentos,
				LancamentoResponseTO.class);
		return responseService.ok(lancamentosResponseTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseTO<LancamentoResponseTO>> alterar(@PathVariable Long id,
			@RequestBody @Valid LancamentoRequestTO lancamentoRequestTO) {
		Lancamento lancamento = converterService.converter(lancamentoRequestTO, Lancamento.class);
		Lancamento lancamentoAtualizado = lancamentoService.atualizar(id, lancamento);
		LancamentoResponseTO lancamentoResponseTO = converterService.converter(lancamentoAtualizado,
				LancamentoResponseTO.class);
		return responseService.ok(lancamentoResponseTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		lancamentoService.deletar(id);
	}

}
