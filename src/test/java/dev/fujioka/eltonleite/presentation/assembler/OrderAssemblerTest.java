package dev.fujioka.eltonleite.presentation.assembler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.eltonleite.domain.model.order.Order;
import dev.fujioka.eltonleite.presentation.dto.order.OrderRequestTO;
import dev.fujioka.eltonleite.presentation.dto.order.OrderResponseTO;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Assembler: Compras")
class OrderAssemblerTest {


    @Test
    @DisplayName("Deve converter uma compra em um DTO de resposta")
    void testEntityToResponseTO() {
        Order entity = new Order(LocalDateTime.now(), 1L);
        entity.setId(1L);
        OrderResponseTO responseTO = OrderAssembler.from(entity);

        assertThat(responseTO).isNotNull();
        assertThat(responseTO.getId()).isNotNull();
        assertThat(responseTO.getDateOrder()).isEqualTo(entity.getDateOrder());
        assertThat(responseTO.getIdUser()).isEqualTo(entity.getIdUser());
    }
    
    @Test
    @DisplayName("Deve converter uma requisição em uma compra")
    void testRequestToEntity() {
        OrderRequestTO requestTO = new OrderRequestTO();
        requestTO.setDateOrder(LocalDateTime.now());
        requestTO.setIdUser(1L);
        Order entity = OrderAssembler.from(requestTO);

        assertThat(entity).isNotNull();
        assertThat(entity.getDateOrder()).isEqualTo(requestTO.getDateOrder());
        assertThat(entity.getIdUser()).isEqualTo(requestTO.getIdUser());
    }

}
