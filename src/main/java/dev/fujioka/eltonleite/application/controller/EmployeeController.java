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

import dev.fujioka.eltonleite.domain.model.employee.Employee;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.EmployeeAssembler;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeRequestTO;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeResponseTO;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private BaseService<Employee> service;
    
    @Autowired
    private ResponseService responseService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<EmployeeResponseTO>> find(@PathVariable Long id) {
        return responseService.ok(EmployeeAssembler.from(service.findBy(id)));
    }
    
    @GetMapping
    public ResponseEntity<ResponseTO<List<EmployeeResponseTO>>> findAll() {
        return responseService.ok(EmployeeAssembler.from(service.findAll()));
    }

    @PostMapping
    public ResponseEntity<ResponseTO<EmployeeResponseTO>> save(@RequestBody EmployeeRequestTO requestTO) {
        Employee employee = EmployeeAssembler.from(requestTO);
        return responseService.ok(EmployeeAssembler.from(service.save(employee)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<EmployeeResponseTO>> update(@PathVariable Long id, @RequestBody EmployeeRequestTO requestTO) {
        Employee employee = EmployeeAssembler.from(requestTO);
        return responseService.ok(EmployeeAssembler.from(service.update(id, employee)));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
