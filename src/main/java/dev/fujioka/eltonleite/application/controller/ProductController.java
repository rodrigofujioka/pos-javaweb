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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.eltonleite.domain.model.product.Product;
import dev.fujioka.eltonleite.domain.service.ProductService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.ProductAssembler;
import dev.fujioka.eltonleite.presentation.dto.product.ProductRequestTO;
import dev.fujioka.eltonleite.presentation.dto.product.ProductResponseTO;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService service;
    
    @Autowired
    private ResponseService responseService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<ProductResponseTO>> find(@PathVariable Long id) {
        return responseService.ok(ProductAssembler.from(service.findBy(id)));
    }
    
    @GetMapping
    public ResponseEntity<ResponseTO<List<ProductResponseTO>>> findAll() {
        return responseService.ok(ProductAssembler.from(service.findAll()));
    }
    
    @GetMapping(params = "manufactureYear")
    public ResponseEntity<ResponseTO<List<ProductResponseTO>>> findAll(@RequestParam(value = "manufactureYear") Integer manufactureYear) {
        return responseService.ok(ProductAssembler.from(service.findByManufactureYear(manufactureYear)));
    }
    
    @GetMapping(params = {"startYear", "endYear",})
    public ResponseEntity<ResponseTO<List<ProductResponseTO>>> findAll(@RequestParam(value = "startYear") Integer startYear, @RequestParam(value = "endYear") Integer endYear) {
        return responseService.ok(ProductAssembler.from(service.findByManufactureYearBetween(startYear, endYear)));
    }
    

    @PostMapping
    public ResponseEntity<ResponseTO<ProductResponseTO>> save(@RequestBody ProductRequestTO requestTO) {
        Product product = ProductAssembler.from(requestTO);
        return responseService.ok(ProductAssembler.from(service.save(product)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<ProductResponseTO>> update(@PathVariable Long id, @RequestBody ProductRequestTO requestTO) {
        Product product = ProductAssembler.from(requestTO);
        return responseService.ok(ProductAssembler.from(service.update(id, product)));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
