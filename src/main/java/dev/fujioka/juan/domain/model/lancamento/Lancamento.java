package dev.fujioka.juan.domain.model.lancamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import dev.fujioka.juan.domain.model.funcionario.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_lancamento")
@Getter
@Setter
@NoArgsConstructor
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "data")
	private LocalDateTime data;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "localizacao")
	private String localizacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Tipo tipo;
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;

	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@PrePersist
	public void prePersist() {
		dataCriacao = LocalDate.now();
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = LocalDate.now();
	}

}
