package dev.fujioka.fagnerlima.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.service.ClientService;
import dev.fujioka.fagnerlima.service.CrudInterface;

@RestController
@RequestMapping("/api/clients")
public class ClientResource extends BaseCrudResource<Client> {

    @Autowired
    private ClientService clientService;

    @Override
    @GetMapping
    public ResponseEntity<Page<Client>> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Client> save(@RequestBody @Valid Client entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping
    public ResponseEntity<Client> update(@RequestBody @Valid Client entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        super.deleteById(id);
    }

    @Override
    protected CrudInterface<Client> getService() {
        return clientService;
    }
}
