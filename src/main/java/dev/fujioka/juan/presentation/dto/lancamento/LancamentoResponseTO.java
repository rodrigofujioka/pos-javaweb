package dev.fujioka.juan.presentation.dto.lancamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.fujioka.juan.domain.model.lancamento.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoResponseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private LocalDateTime data;
	private String descricao;
	private String localizacao;
	private Tipo tipo;
	private LocalDate dataCriacao;
	private LocalDate dataAtualizacao;

}
