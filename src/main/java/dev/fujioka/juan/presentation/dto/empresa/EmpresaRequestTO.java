package dev.fujioka.juan.presentation.dto.empresa;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaRequestTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String razaoSocial;
	@NotNull
	private String cnpj;

}
