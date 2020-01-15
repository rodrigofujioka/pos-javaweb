package dev.fujioka.eltonleite.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.fujioka.eltonleite.domain.model.order.Order;
import dev.fujioka.eltonleite.domain.service.BaseService;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Serviço de Compras")
class OrderServiceImplTest {

    @Autowired
    private BaseService<Order> service;

    @Test
    @DisplayName("Deve ser possível criar um compra")
    void testSave() {
        Order order = new Order(LocalDateTime.now(), 1L);
        Order orderSaved = service.save(order);

        assertThat(orderSaved).isNotNull();
        assertThat(orderSaved.getId()).isNotNull();
        assertThat(orderSaved.getDateOrder()).isEqualTo(order.getDateOrder());
        assertThat(orderSaved.getIdUser()).isEqualTo(order.getIdUser());
    }

    @Test
    @DisplayName("Deve ser possível atualizar um compra")
    void testUpdate() {
        Order orderSaved = service.save(new Order(LocalDateTime.now(), 1L));

        Order orderUpdated = service.update(orderSaved.getId(), new Order(LocalDateTime.now(), 2L));

        assertThat(orderUpdated).isNotNull();
        assertThat(orderSaved.getId()).isEqualTo(orderSaved.getId());
        assertThat(orderUpdated.getDateOrder()).isNotEqualTo(orderSaved.getDateOrder());
        assertThat(orderUpdated.getIdUser()).isNotEqualTo(orderSaved.getIdUser());
    }

    @Test
    @DisplayName("Deve ser possível listar todos os compras")
    void testFindAll() {
        service.save(new Order(LocalDateTime.now(), 1L));
        service.save(new Order(LocalDateTime.now(), 1L));
        service.save(new Order(LocalDateTime.now(), 1L));
        service.save(new Order(LocalDateTime.now(), 1L));

        List<Order> orders = service.findAll();

        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deve ser possível buscar um compra pelo seu id")
    void testFindById() {
        Long id = service.save(new Order(LocalDateTime.now(), 1L)).getId();
        service.save(new Order(LocalDateTime.now(), 1L));
        service.save(new Order(LocalDateTime.now(), 1L));
        service.save(new Order(LocalDateTime.now(), 1L));

        Order order = service.findBy(id);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Deve ser possível excluir um compra")
    @ExceptionHandler(RuntimeException.class)
    void testDelete() {
        Long id = service.save(new Order(LocalDateTime.now(), 1L)).getId();
        service.delete(id);
        
        RuntimeException exception =
                assertThrows(RuntimeException.class,
                   () -> service.findBy(id));
        
        
        String expectedMessage = "Compra inexistente";
        String actualMessage = exception.getMessage();
     
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
