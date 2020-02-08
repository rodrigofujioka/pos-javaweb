package dev.fujioka.juan.application.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.fujioka.juan.application.service.exception.EmpresaNaoEncontradaException;
import dev.fujioka.juan.application.service.exception.InformacaoNaoEncontradaException;
import dev.fujioka.juan.infrastructure.service.MessageServiceImpl;
import dev.fujioka.juan.presentation.dto.ResponseTO;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageServiceImpl messageService;

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, request, "erro.interno");
	}

	@ExceptionHandler({ InformacaoNaoEncontradaException.class })
	public ResponseEntity<Object> handleInformacaoNaoEncontradaException(InformacaoNaoEncontradaException ex,
			WebRequest request) {
		return handleException(ex, HttpStatus.NOT_FOUND, request, "recurso.informacao-nao-encontrada");
	}
	
	@ExceptionHandler({ EmpresaNaoEncontradaException.class })
	public ResponseEntity<Object> handleEmpresaNaoEncontradaException(EmpresaNaoEncontradaException ex,
			WebRequest request) {
		return handleException(ex, HttpStatus.NOT_FOUND, request, "funcionario.empresa-nao-encontrada");
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseTO<String> response = new ResponseTO<>();
		List<String> erros = obterListaErros(ex.getBindingResult());
		response.setErros(erros);
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<String> obterListaErros(BindingResult bindingResult) {
		List<String> erros = new ArrayList<>();
		bindingResult.getFieldErrors().forEach(e -> erros.add(messageService.getMessage(e)));
		return erros;

	}

	protected ResponseEntity<Object> handleException(Exception ex, HttpStatus status, WebRequest req, String chave) {
		ResponseTO<List<String>> response = new ResponseTO<>();
		response.setErros(Arrays.asList((messageService.getMessage(chave))));
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, req);
	}
}
