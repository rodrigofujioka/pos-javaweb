package dev.fujioka.eltonleite.presentation.assembler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.eltonleite.domain.model. product. Product;
import dev.fujioka.eltonleite.presentation.dto. product. ProductRequestTO;
import dev.fujioka.eltonleite.presentation.dto. product. ProductResponseTO;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Assembler: Produtos")
class ProductAssemblerTest {


    @Test
    @DisplayName("Deve converter um produto em um DTO de resposta")
    void testEntityToResponseTO() {
         Product entity = new  Product("Teste 1", "Teste 1", 1991);
        entity.setId(1L);
         ProductResponseTO responseTO =  ProductAssembler.from(entity);

        assertThat(responseTO).isNotNull();
        assertThat(responseTO.getId()).isNotNull();
        assertThat(responseTO.getName()).isEqualTo(entity.getName());
        assertThat(responseTO.getDescription()).isEqualTo(entity.getDescription());
        assertThat(responseTO.getManufactureYear()).isEqualTo(entity.getManufactureYear());
    }
    
    @Test
    @DisplayName("Deve converter uma requisição em um produto")
    void testRequestToEntity() {
         ProductRequestTO requestTO = new  ProductRequestTO();
        requestTO.setName("Teste 1");
        requestTO.setDescription("Teste 1");
        requestTO.setManufactureYear(1991);
         Product entity =  ProductAssembler.from(requestTO);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo(requestTO.getName());
        assertThat(entity.getDescription()).isEqualTo(requestTO.getDescription());
        assertThat(entity.getManufactureYear()).isEqualTo(requestTO.getManufactureYear());
    }

}
