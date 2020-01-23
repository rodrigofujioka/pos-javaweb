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

import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.service.CrudInterface;
import dev.fujioka.fagnerlima.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreResource extends BaseCrudResource<Store> {

    @Autowired
    private StoreService storeService;

    @Override
    @GetMapping
    public ResponseEntity<Page<Store>> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Store> save(@RequestBody @Valid Store entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping
    public ResponseEntity<Store> update(@RequestBody @Valid Store entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        super.deleteById(id);
    }

    @Override
    protected CrudInterface<Store> getService() {
        return storeService;
    }
}
