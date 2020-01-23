package dev.fujioka.fagnerlima.web.rest;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import dev.fujioka.fagnerlima.domain.Sale;
import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.service.SaleService;
import dev.fujioka.fagnerlima.web.dto.SaleRequestTO;
import dev.fujioka.fagnerlima.web.dto.SaleResponseTO;

@RestController
@RequestMapping("/api/sales")
public class SaleResource {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<SaleResponseTO>> findAll(Pageable pageable) {
        Page<Sale> salesPage = saleService.findAll(pageable);
        Page<SaleResponseTO> salesResponseTOPage = salesPage.map(r -> modelMapper.map(r, SaleResponseTO.class));

        return ResponseEntity.ok(salesResponseTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseTO> findById(@PathVariable Long id) {
        Sale sale = saleService.findById(id).orElseThrow(() -> new EntityNotFoundException("id", id, "Informação não encontrada"));
        SaleResponseTO saleResponseTO = modelMapper.map(sale, SaleResponseTO.class);

        return ResponseEntity.ok(saleResponseTO);
    }

    @PostMapping
    public ResponseEntity<SaleResponseTO> save(@RequestBody @Valid SaleRequestTO saleRequestTO) {
        Sale sale = modelMapper.map(saleRequestTO, Sale.class);
        Sale saleSaved = saleService.save(sale).orElseThrow(() -> new RuntimeException());
        SaleResponseTO saleResponseTO = modelMapper.map(saleSaved, SaleResponseTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponseTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        saleService.deleteById(id);
    }
}
