package dev.fujioka.juan.presentation.dto.produto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String descricao;
	private int anoFabricacao;

}
