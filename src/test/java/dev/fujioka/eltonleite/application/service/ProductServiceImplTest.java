package dev.fujioka.eltonleite.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.fujioka.eltonleite.domain.model.product.Product;
import dev.fujioka.eltonleite.domain.service.ProductService;
import dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository.ProductRepository;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Serviço de Produtos")
class ProductServiceImplTest {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @BeforeEach
    private void before() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Deve ser possível criar um produto")
    void testSave() {
        Product product = new Product("Teste 1", "Teste 1", 1991);
        Product productSaved = service.save(product);

        assertThat(productSaved).isNotNull();
        assertThat(productSaved.getId()).isNotNull();
        assertThat(productSaved.getName()).isEqualTo(product.getName());
        assertThat(productSaved.getDescription()).isEqualTo(product.getDescription());
        assertThat(productSaved.getManufactureYear()).isEqualTo(product.getManufactureYear());
    }

    @Test
    @DisplayName("Deve ser possível atualizar um produto")
    void testUpdate() {
        Product productSaved = service.save(new Product("Teste 1", "Teste 1", 1991));

        Product productUpdated = service.update(productSaved.getId(), new Product("Teste 2", "Teste 2", 1992));

        assertThat(productUpdated).isNotNull();
        assertThat(productUpdated.getId()).isEqualTo(productSaved.getId());
        assertThat(productUpdated.getName()).isNotEqualTo(productSaved.getName());
        assertThat(productUpdated.getDescription()).isNotEqualTo(productSaved.getDescription());
        assertThat(productUpdated.getManufactureYear()).isNotEqualTo(productSaved.getManufactureYear());
    }

    @Test
    @DisplayName("Deve ser possível listar todos os produtos")
    void testFindAll() {
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 1", "Teste 1", 1991));

        List<Product> products = service.findAll();

        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deve ser possível listar produtos por seu ano de fabricação")
    void testFindByManufactureYear() {
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 2", "Teste 2", 1992));
        service.save(new Product("Teste 1", "Teste 1", 1993));
        service.save(new Product("Teste 1", "Teste 1", 1994));

        List<Product> products = service.findByManufactureYear(1992);

        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0)).isNotNull();
        assertThat(products.get(0).getId()).isNotNull();
        assertThat(products.get(0).getName()).isEqualTo("Teste 2");
        assertThat(products.get(0).getDescription()).isEqualTo("Teste 2");
        assertThat(products.get(0).getManufactureYear()).isEqualTo(1992);
    }
    
    @Test
    @DisplayName("Deve ser possível listar produtos que foram fabricados entre um periodo")
    void testFindByManufactureYearBetween() {
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 2", "Teste 2", 1992));
        service.save(new Product("Teste 1", "Teste 1", 1993));
        service.save(new Product("Teste 1", "Teste 1", 1994));

        List<Product> products = service.findByManufactureYearBetween(1991, 1993);

        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve ser possível buscar um produto pelo seu id")
    void testFindById() {
        Long id = service.save(new Product("Teste 1", "Teste 1", 1991)).getId();
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 1", "Teste 1", 1991));
        service.save(new Product("Teste 1", "Teste 1", 1991));

        Product product = service.findBy(id);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Deve ser possível excluir um produto")
    @ExceptionHandler(RuntimeException.class)
    void testDelete() {
        Long id = service.save(new Product("Teste 1", "Teste 1", 1991)).getId();
        service.delete(id);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findBy(id));

        String expectedMessage = "Produto inexistente";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
