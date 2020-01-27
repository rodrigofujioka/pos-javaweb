package dev.fujioka.juan.presentation.dto.empresa;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaResponseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String razaoSocial;
	private String cnpj;
	private LocalDate dataCriacao;
	private LocalDate dataAtualizacao;

}
