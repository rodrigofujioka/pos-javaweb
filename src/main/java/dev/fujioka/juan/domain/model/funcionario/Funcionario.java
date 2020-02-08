package dev.fujioka.juan.domain.model.funcionario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import dev.fujioka.juan.domain.model.empresa.Empresa;
import dev.fujioka.juan.domain.model.lancamento.Lancamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_funcionario")
@NoArgsConstructor
@Getter
@Setter
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 8440222314211474189L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "email")
	private String email;
	@Column(name = "senha")
	private String senha;
	@Column(name = "valor_hora")
	private BigDecimal valorHora;
	@Column(name = "qtd_horas_trabalho_dia")
	private Double qtdHorasTrabalhoDia;
	@Column(name = "qtd_horas_almoco")
	private Double qtdHorasAlmoco;
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil")
	private Perfil perfil;
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@PrePersist
	public void prePersist() {
		dataCriacao = LocalDate.now();
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = LocalDate.now();
	}

}
