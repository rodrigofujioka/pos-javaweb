package dev.fujioka.juan.presentation.dto.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import dev.fujioka.juan.domain.model.funcionario.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioRequestTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String nome;
	@CPF
	private String cpf;
	@Email
	private String email;
	private String senha;
	private BigDecimal valorHora;
	private Double qtdHorasTrabalhoDia;
	private Double qtdHorasAlmoco;
	private Perfil perfil;
	private Long empresaId;

}
