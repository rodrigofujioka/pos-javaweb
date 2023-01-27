package dev.fujioka.java.avancado.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private String matricula;

}
