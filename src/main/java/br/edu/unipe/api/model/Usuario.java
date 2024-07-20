package br.edu.unipe.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id;
    private String email;
    private String nome;
}
