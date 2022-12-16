package dev.fujioka.java.avancado.web.resource;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unipe")
public class UnipeResource {


    @GetMapping()
    public ResponseEntity<String> chamadaGet(){
        return ResponseEntity.ok("olá");
    }

    @GetMapping("/turma20222")
    public ResponseEntity<String> chamadaTurmaGet(){
        return ResponseEntity.ok("olá turma 2022.2");
    }
}
