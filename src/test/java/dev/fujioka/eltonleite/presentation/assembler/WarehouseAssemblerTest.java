package dev.fujioka.eltonleite.presentation.assembler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.eltonleite.domain.model. warehouse. Warehouse;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseRequestTO;
import dev.fujioka.eltonleite.presentation.dto.warehouse.WarehouseResponseTO;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Assembler: Armazéns")
class WarehouseAssemblerTest {


    @Test
    @DisplayName("Deve converter um armazém em um DTO de resposta")
    void testEntityToResponseTO() {
         Warehouse entity = new  Warehouse("Teste 1", "Teste 1");
        entity.setId(1L);
         WarehouseResponseTO responseTO =  WarehouseAssembler.from(entity);

        assertThat(responseTO).isNotNull();
        assertThat(responseTO.getId()).isNotNull();
        assertThat(responseTO.getName()).isEqualTo(entity.getName());
        assertThat(responseTO.getAddress()).isEqualTo(entity.getAddress());
    }
    
    @Test
    @DisplayName("Deve converter uma requisição em um armazém")
    void testRequestToEntity() {
         WarehouseRequestTO requestTO = new  WarehouseRequestTO();
        requestTO.setName("Teste 1");
        requestTO.setAddress("Teste 1");
         Warehouse entity =  WarehouseAssembler.from(requestTO);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo(requestTO.getName());
        assertThat(entity.getAddress()).isEqualTo(requestTO.getAddress());
    }

}
