package br.edu.unipe.api.controller;

import br.edu.unipe.api.model.Usuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping
    public Usuario  getUsuario(int id){
        return new Usuario(1,"@","Rodrigo");
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        return usuario;
    }

    @PutMapping
    public Usuario putUsuario(@RequestBody Usuario usuario){
        return usuario;
    }

    @DeleteMapping
    public void deleteUsuario(){

    }

}
