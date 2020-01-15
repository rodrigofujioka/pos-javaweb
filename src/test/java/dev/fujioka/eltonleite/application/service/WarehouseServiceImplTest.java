package dev.fujioka.eltonleite.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.fujioka.eltonleite.domain.model.warehouse.Warehouse;
import dev.fujioka.eltonleite.domain.service.BaseService;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Serviço de Armazéns")
class WarehouseServiceImplTest {

    @Autowired
    private BaseService<Warehouse> service;

    @Test
    @DisplayName("Deve ser possível criar um armazém")
    void testSave() {
        Warehouse warehouse = new Warehouse("Teste 1", "Teste 1");
        Warehouse warehouseSaved = service.save(warehouse);

        assertThat(warehouseSaved).isNotNull();
        assertThat(warehouseSaved.getId()).isNotNull();
        assertThat(warehouseSaved.getName()).isEqualTo(warehouse.getName());
        assertThat(warehouseSaved.getAddress()).isEqualTo(warehouse.getAddress());
    }

    @Test
    @DisplayName("Deve ser possível atualizar um armazém")
    void testUpdate() {
        Warehouse warehouseSaved = service.save(new Warehouse("Teste A", "Teste 1"));

        Warehouse warehouseUpdated = service.update(warehouseSaved.getId(), new Warehouse("Teste U", "Teste U"));

        assertThat(warehouseUpdated).isNotNull();
        assertThat(warehouseSaved.getId()).isEqualTo(warehouseSaved.getId());
        assertThat(warehouseUpdated.getName()).isNotEqualTo(warehouseSaved.getName());
        assertThat(warehouseUpdated.getAddress()).isNotEqualTo(warehouseSaved.getAddress());
    }

    @Test
    @DisplayName("Deve ser possível listar todos os armazéms")
    void testFindAll() {
        service.save(new Warehouse("Teste A", "Teste A"));
        service.save(new Warehouse("Teste B", "Teste B"));
        service.save(new Warehouse("Teste C", "Teste C"));
        service.save(new Warehouse("Teste D", "Teste D"));

        List<Warehouse> warehouses = service.findAll();

        assertThat(warehouses).isNotNull();
        assertThat(warehouses.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deve ser possível buscar um armazém pelo seu id")
    void testFindById() {
        Long id = service.save(new Warehouse("Teste A", "Teste A")).getId();
        service.save(new Warehouse("Teste B", "Teste B"));
        service.save(new Warehouse("Teste C", "Teste C"));
        service.save(new Warehouse("Teste D", "Teste D"));

        Warehouse warehouse = service.findBy(id);

        assertThat(warehouse).isNotNull();
        assertThat(warehouse.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Deve ser possível excluir um armazém")
    @ExceptionHandler(RuntimeException.class)
    void testDelete() {
        Long id = service.save(new Warehouse("Teste A", "Teste 1")).getId();
        service.delete(id);
        
        RuntimeException exception =
                assertThrows(RuntimeException.class,
                   () -> service.findBy(id));
        
        
        String expectedMessage = "Armazém inexistente";
        String actualMessage = exception.getMessage();
     
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
