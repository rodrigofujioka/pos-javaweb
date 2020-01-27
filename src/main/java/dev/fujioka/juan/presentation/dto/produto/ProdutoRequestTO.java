package dev.fujioka.juan.presentation.dto.produto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProdutoRequestTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@NotNull(message = "{nome.notNull}")
	private String nome;
	@NotNull(message = "{descricao.notNull}")
	private String descricao;
	@NotNull(message = "{ano.notNull}")
	private Integer anoFabricacao;

}
