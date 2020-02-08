package dev.fujioka.juan.presentation.dto.user;

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
	@SearchParam(propriedade = "username", operacao = Operacao.LIKE)
	private String username;

}
