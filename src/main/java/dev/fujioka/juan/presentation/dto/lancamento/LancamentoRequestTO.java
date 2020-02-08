package dev.fujioka.juan.presentation.dto.lancamento;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import dev.fujioka.juan.domain.model.lancamento.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoRequestTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private LocalDateTime data;
	@NotNull
	private String descricao;
	@NotNull
	private String localizacao;
	@NotNull
	private Tipo tipo;

}
