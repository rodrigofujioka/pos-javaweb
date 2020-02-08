package dev.fujioka.juan.domain.model.empresa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import dev.fujioka.juan.domain.model.funcionario.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 3596876013556564541L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	@Column(name = "data_atualizacao")
	private LocalDate dataAtualizacao;
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;

	@PrePersist
	public void prePersist() {
		dataCriacao = LocalDate.now();
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = LocalDate.now();
	}

}
