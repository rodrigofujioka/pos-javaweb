package dev.fujioka.fagnerlima.service;

import static dev.fujioka.fagnerlima.service.ServiceAssertions.assertPage;
import static dev.fujioka.fagnerlima.service.ServiceAssertions.assertPageNoContent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.fagnerlima.domain.Product;
import dev.fujioka.fagnerlima.repository.ProductRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        generateAndSaveProducts(4);
        List<Product> products = productService.findAll();

        assertThat(products.size()).isEqualTo(4);
    }

    @Test
    public void testFindAllByPageable() {
        generateAndSaveProducts(15);
        Page<Product> productsPage = productService.findAll(PageRequest.of(0, 10));

        assertPage(productsPage, 10, 0, 10, 2, 15);
    }

    @Test
    public void testFindAllByPageable_noContent() {
        Page<Product> productsPage = productService.findAll(PageRequest.of(0, 10));

        assertPageNoContent(productsPage, 10, 0);
    }

    @Test
    public void findById() {
        Product mockProduct = generateAndSaveProduct();
        Product product = productService.findById(mockProduct.getId()).orElseThrow();

        assertThat(product.getId()).isEqualTo(mockProduct.getId());
    }

    @Test
    public void findById_NotFound() {
        assertThrows(NoSuchElementException.class, () -> productService.findById(1000L).orElseThrow());
    }

    @Test
    public void testSave() {
        Product product = generateProduct();

        productService.save(product).orElseThrow();

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isNotBlank();
        assertThat(product.getDescription()).isNotBlank();
        assertThat(product.getQuantity()).isGreaterThan(0);
    }

    @Test
    public void testUpdate() {
        Product mockProduct = generateAndSaveProduct();

        Product updatedProduct = new Product();
        BeanUtils.copyProperties(mockProduct, updatedProduct);
        updatedProduct.setName("Modified");
        updatedProduct.setDescription("Modified desc");
        productService.save(updatedProduct).orElseThrow();

        assertThat(updatedProduct.getId()).isEqualTo(mockProduct.getId());
        assertThat(updatedProduct.getName()).isNotEqualTo(mockProduct.getName());
        assertThat(updatedProduct.getDescription()).isNotEqualTo(mockProduct.getDescription());
        assertThat(updatedProduct.getQuantity()).isEqualTo(mockProduct.getQuantity());
    }

    @Test
    public void testDelete() {
        Product mockProduct = generateAndSaveProduct();

        assertThatCode(() -> productService.deleteById(mockProduct.getId())).doesNotThrowAnyException();
    }

    @Test
    public void testDelete_whenNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> productService.deleteById(100L));
    }

    private Product generateProduct() {
        return new Product(RandomString.make(10), RandomString.make(10), 10);
    }

    private Product generateAndSaveProduct() {
        return productRepository.save(generateProduct());
    }

    private List<Product> generateProducts(Integer quantity) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            products.add(new Product(RandomString.make(10), RandomString.make(10), 10));
        }

        return products;
    }

    private List<Product> generateAndSaveProducts(Integer quantity) {
        return productRepository.saveAll(generateProducts(quantity));
    }

}
