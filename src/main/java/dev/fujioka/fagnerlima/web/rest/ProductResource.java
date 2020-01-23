package dev.fujioka.fagnerlima.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.fagnerlima.domain.Product;
import dev.fujioka.fagnerlima.service.ProductService;
import dev.fujioka.fagnerlima.web.dto.ProductAmountRequestTO;

@RestController
@RequestMapping("/api")
public class ProductResource {

    @Autowired
    private ProductService productService;


    @GetMapping("/product")
    public List<Product> getProductList() {

        return productService.findAll();
    }

    @PostMapping("/product")
    public ResponseEntity<Product> save(
            @RequestBody Product product) {

        product = productService.save(product).get() ;

        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> update(
            @RequestBody Product product) {

        product = productService.save(product).get();

        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/product")
    public ResponseEntity<String> delete(
            @RequestBody Product product) {

        productService.delete(product);
        return ResponseEntity.ok().body("Product excluded " + product.getId());
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> addAmount(@PathVariable Long id, @RequestBody ProductAmountRequestTO requestTO) {
        Product product = productService.addAmount(id, requestTO.getAmount()).get();

        return ResponseEntity.ok(product);
    }
}
