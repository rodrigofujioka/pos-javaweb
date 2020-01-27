package dev.fujioka.juan.presentation.dto.produto;

import java.io.Serializable;
import java.util.List;

import dev.fujioka.juan.infrastructure.specification.Operacao;
import dev.fujioka.juan.infrastructure.specification.annotation.SearchParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscaRequestTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@SearchParam(propriedade = "id")
	List<Long> ids;
	@SearchParam(propriedade = "nome", operacao = Operacao.LIKE)
	private String nome;
	@SearchParam(propriedade = "descricao")
	private String descricao;
	@SearchParam(propriedade = "anoFabricacao")
	private Integer anoFabricacao;

}
