package dev.fujioka.juan.application.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.juan.domain.model.user.User;
import dev.fujioka.juan.domain.service.UserService;
import dev.fujioka.juan.infrastructure.service.ConverterServiceImpl;
import dev.fujioka.juan.infrastructure.service.ResponseServiceImpl;
import dev.fujioka.juan.infrastructure.specification.SpecificationBuilder;
import dev.fujioka.juan.presentation.dto.ResponseTO;
import dev.fujioka.juan.presentation.dto.user.BuscaRequestTO;
import dev.fujioka.juan.presentation.dto.user.UserRequestTO;
import dev.fujioka.juan.presentation.dto.user.UserResponseTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private SpecificationBuilder specificationBuilder;

	@Autowired
	private ConverterServiceImpl converterService;

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseServiceImpl responseService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseTO<UserResponseTO>> buscar(@PathVariable Long id) {
		User usuario = userService.buscar(id);
		UserResponseTO userResponseTO = converterService.converter(usuario, UserResponseTO.class);
		return responseService.ok(userResponseTO);

	}

	@GetMapping
	public ResponseEntity<ResponseTO<Page<UserResponseTO>>> listar(BuscaRequestTO busca, Pageable pagina) {
		Specification<User> predicate = specificationBuilder.criarFiltro(busca, User.class);
		Page<User> usuarios = userService.listarTodos(predicate, pagina);
		Page<UserResponseTO> usersResponseTO = converterService.converter(usuarios, UserResponseTO.class);
		return responseService.ok(usersResponseTO);

	}

	@PostMapping
	public ResponseEntity<ResponseTO<UserResponseTO>> salvar(@RequestBody UserRequestTO userRequestTO) {
		User usuario = converterService.converter(userRequestTO, User.class);
		User usuarioSalvo = userService.salvar(usuario);
		UserResponseTO userResponseTO = converterService.converter(usuarioSalvo, UserResponseTO.class);
		return responseService.create(userResponseTO);

	}


	@PutMapping("/{id}")
	public ResponseEntity<ResponseTO<UserResponseTO>> alterar(@PathVariable Long id, @RequestBody UserRequestTO userRequestTO) {
		User usuario = converterService.converter(userRequestTO, User.class);
		User usuarioAtualizado = userService.atualizar(id, usuario);
		UserResponseTO userResponseTO = converterService.converter(usuarioAtualizado, UserResponseTO.class);
		return responseService.ok(userResponseTO);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		userService.deletar(id);
	}

}
