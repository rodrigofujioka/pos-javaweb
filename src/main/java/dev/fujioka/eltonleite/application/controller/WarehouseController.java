package dev.fujioka.eltonleite.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import dev.fujioka.eltonleite.domain.model.warehouse.Warehouse;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.WarehouseAssembler;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseRequestTO;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseResponseTO;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private BaseService<Warehouse> service;
    
    @Autowired
    private ResponseService responseService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<WarehouseResponseTO>> find(@PathVariable Long id) {
        return responseService.ok(WarehouseAssembler.from(service.findBy(id)));
    }
    
    @GetMapping
    public ResponseEntity<ResponseTO<List<WarehouseResponseTO>>> findAll() {
        return responseService.ok(WarehouseAssembler.from(service.findAll()));
    }

    @PostMapping
    public ResponseEntity<ResponseTO<WarehouseResponseTO>> save(@RequestBody WarehouseRequestTO requestTO) {
        Warehouse warehouse = WarehouseAssembler.from(requestTO);
        return responseService.ok(WarehouseAssembler.from(service.save(warehouse)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<WarehouseResponseTO>> update(@PathVariable Long id, @RequestBody WarehouseRequestTO requestTO) {
        Warehouse warehouse = WarehouseAssembler.from(requestTO);
        return responseService.ok(WarehouseAssembler.from(service.update(id, warehouse)));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
