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

import dev.fujioka.juan.domain.model.funcionario.Funcionario;
import dev.fujioka.juan.domain.service.FuncionarioService;
import dev.fujioka.juan.infrastructure.service.ConverterServiceImpl;
import dev.fujioka.juan.infrastructure.service.ResponseServiceImpl;
import dev.fujioka.juan.presentation.dto.ResponseTO;
import dev.fujioka.juan.presentation.dto.funcionario.FuncionarioRequestTO;
import dev.fujioka.juan.presentation.dto.funcionario.FuncionarioResponseTO;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private ConverterServiceImpl converterService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private ResponseServiceImpl responseService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTO<FuncionarioResponseTO>> buscar(@PathVariable Long id) {
		Funcionario funcionario = funcionarioService.buscar(id);
		FuncionarioResponseTO funcionarioResponseTO = converterService.converter(funcionario,
				FuncionarioResponseTO.class);
		return responseService.ok(funcionarioResponseTO);

	}

	@PostMapping
	public ResponseEntity<ResponseTO<FuncionarioResponseTO>> salvar(@RequestBody @Valid FuncionarioRequestTO funcionarioRequestTO) {
		Funcionario funcionario = converterService.converter(funcionarioRequestTO, Funcionario.class);
		Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario);
		FuncionarioResponseTO funcionarioResponseTO = converterService.converter(funcionarioSalvo,
				FuncionarioResponseTO.class);
		return responseService.create(funcionarioResponseTO);

	}

	@GetMapping
	public ResponseEntity<ResponseTO<Page<FuncionarioResponseTO>>> listar(Pageable pagina) {
		Page<Funcionario> funcionarios = funcionarioService.listarTodos(pagina);
		Page<FuncionarioResponseTO> funcionariosResponseTO = converterService.converter(funcionarios,
				FuncionarioResponseTO.class);
		return responseService.ok(funcionariosResponseTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseTO<FuncionarioResponseTO>> alterar(@PathVariable Long id,
			@RequestBody FuncionarioRequestTO funcionarioRequestTO) {
		Funcionario funcionario = converterService.converter(funcionarioRequestTO, Funcionario.class);
		Funcionario funcionarioAtualizado = funcionarioService.atualizar(id, funcionario);
		FuncionarioResponseTO funcionarioResponseTO = converterService.converter(funcionarioAtualizado,
				FuncionarioResponseTO.class);
		return responseService.ok(funcionarioResponseTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		funcionarioService.deletar(id);
	}

}
