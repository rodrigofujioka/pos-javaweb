package dev.fujioka.eltonleite.presentation.assembler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.eltonleite.domain.model.employee.Employee;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeRequestTO;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeResponseTO;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Assembler: Funcionários")
class EmployeeAssemblerTest {


    @Test
    @DisplayName("Deve converter um funcionário em um DTO de resposta")
    void testEntityToResponseTO() {
        Employee entity = new Employee("Teste 1", LocalDate.of(1991, 6, 18));
        entity.setId(1L);
        EmployeeResponseTO responseTO = EmployeeAssembler.from(entity);

        assertThat(responseTO).isNotNull();
        assertThat(responseTO.getId()).isNotNull();
        assertThat(responseTO.getId()).isEqualTo(entity.getId());
        assertThat(responseTO.getName()).isEqualTo(entity.getName());
        assertThat(responseTO.getDateBirth()).isEqualTo(entity.getDateBirth());
    }
    
    @Test
    @DisplayName("Deve converter um funcionário em um DTO de resposta")
    void testRequestToEntity() {
        EmployeeRequestTO requestTO = new EmployeeRequestTO();
        requestTO.setName("Teste 1");
        requestTO.setDateBirth(LocalDate.now());
        Employee entity = EmployeeAssembler.from(requestTO);

        assertThat(entity).isNotNull();
        assertThat(entity.getName()).isEqualTo(requestTO.getName());
        assertThat(entity.getDateBirth()).isEqualTo(requestTO.getDateBirth());
    }

}
