package dev.fujioka.juan.presentation.dto.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import dev.fujioka.juan.domain.model.funcionario.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioResponseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private Perfil perfil;
	private LocalDate dataCriacao;
	private LocalDate dataAtualizacao;

}
