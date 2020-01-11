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

import dev.fujioka.eltonleite.domain.model.order.Order;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.order.OrderAssembler;
import dev.fujioka.eltonleite.presentation.dto.order.OrderRequestTO;
import dev.fujioka.eltonleite.presentation.dto.order.OrderResponseTO;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private BaseService<Order> service;
    
    @Autowired
    private ResponseService responseService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<OrderResponseTO>> find(@PathVariable Long id) {
        return responseService.ok(OrderAssembler.from(service.findBy(id)));
    }
    
    @GetMapping
    public ResponseEntity<ResponseTO<List<OrderResponseTO>>> findAll() {
        return responseService.ok(OrderAssembler.from(service.findAll()));
    }

    @PostMapping
    public ResponseEntity<ResponseTO<OrderResponseTO>> save(@RequestBody OrderRequestTO requestTO) {
        Order order = OrderAssembler.from(requestTO);
        return responseService.ok(OrderAssembler.from(service.save(order)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<OrderResponseTO>> update(@PathVariable Long id, @RequestBody OrderRequestTO requestTO) {
        Order order = OrderAssembler.from(requestTO);
        return responseService.ok(OrderAssembler.from(service.update(id, order)));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
