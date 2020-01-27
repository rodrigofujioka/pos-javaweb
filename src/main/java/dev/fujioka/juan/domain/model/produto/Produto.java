package dev.fujioka.juan.domain.model.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "ano_fabricacao")
	private Integer anoFabricacao;

}
