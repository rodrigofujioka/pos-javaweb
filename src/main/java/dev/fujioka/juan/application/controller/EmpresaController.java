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

import dev.fujioka.juan.domain.model.empresa.Empresa;
import dev.fujioka.juan.domain.service.EmpresaService;
import dev.fujioka.juan.infrastructure.service.ConverterServiceImpl;
import dev.fujioka.juan.infrastructure.service.ResponseServiceImpl;
import dev.fujioka.juan.presentation.dto.ResponseTO;
import dev.fujioka.juan.presentation.dto.empresa.EmpresaRequestTO;
import dev.fujioka.juan.presentation.dto.empresa.EmpresaResponseTO;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

	@Autowired
	private ConverterServiceImpl converterService;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private ResponseServiceImpl responseService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTO<EmpresaResponseTO>> buscar(@PathVariable Long id) {
		Empresa empresa = empresaService.buscar(id);
		EmpresaResponseTO empresaResponseTO = converterService.converter(empresa, EmpresaResponseTO.class);
		return responseService.ok(empresaResponseTO);

	}

	@PostMapping
	public ResponseEntity<ResponseTO<EmpresaResponseTO>> salvar(@RequestBody @Valid EmpresaRequestTO EmpresaRequestTO) {
		Empresa empresa = converterService.converter(EmpresaRequestTO, Empresa.class);
		Empresa empresaSalvo = empresaService.salvar(empresa);
		EmpresaResponseTO empresaResponseTO = converterService.converter(empresaSalvo, EmpresaResponseTO.class);
		return responseService.create(empresaResponseTO);

	}

	@GetMapping
	public ResponseEntity<ResponseTO<Page<EmpresaResponseTO>>> listar(Pageable pagina) {
		Page<Empresa> empresas = empresaService.listarTodos(pagina);
		Page<EmpresaResponseTO> empresasResponseTO = converterService.converter(empresas, EmpresaResponseTO.class);
		return responseService.ok(empresasResponseTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseTO<EmpresaResponseTO>> alterar(@PathVariable Long id,
			@RequestBody @Valid EmpresaRequestTO EmpresaRequestTO) {
		Empresa empresa = converterService.converter(EmpresaRequestTO, Empresa.class);
		Empresa empresaAtualizado = empresaService.atualizar(id, empresa);
		EmpresaResponseTO empresaResponseTO = converterService.converter(empresaAtualizado, EmpresaResponseTO.class);
		return responseService.ok(empresaResponseTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		empresaService.deletar(id);
	}

}
