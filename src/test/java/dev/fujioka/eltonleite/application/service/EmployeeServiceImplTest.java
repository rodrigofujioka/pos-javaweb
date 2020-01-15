package dev.fujioka.eltonleite.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.fujioka.eltonleite.domain.model.employee.Employee;
import dev.fujioka.eltonleite.domain.service.BaseService;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Serviço de Funcionários")
class EmployeeServiceImplTest {

    @Autowired
    private BaseService<Employee> service;

    @Test
    @DisplayName("Deve ser possível criar um funcionário")
    void testSave() {
        Employee employee = new Employee("Teste 1", LocalDate.of(1991, 6, 18));
        Employee employeeSaved = service.save(employee);

        assertThat(employeeSaved).isNotNull();
        assertThat(employeeSaved.getId()).isNotNull();
        assertThat(employeeSaved.getName()).isEqualTo(employee.getName());
        assertThat(employeeSaved.getDateBirth()).isEqualTo(employee.getDateBirth());
    }

    @Test
    @DisplayName("Deve ser possível atualizar um funcionário")
    void testUpdate() {
        Employee employeeSaved = service.save(new Employee("Teste A", LocalDate.of(1991, 6, 18)));

        Employee employeeUpdated = service.update(employeeSaved.getId(), new Employee("Teste U", LocalDate.of(1995, 6, 18)));

        assertThat(employeeUpdated).isNotNull();
        assertThat(employeeSaved.getId()).isEqualTo(employeeSaved.getId());
        assertThat(employeeUpdated.getName()).isNotEqualTo(employeeSaved.getName());
        assertThat(employeeUpdated.getDateBirth()).isNotEqualTo(employeeSaved.getDateBirth());
    }

    @Test
    @DisplayName("Deve ser possível listar todos os funcionários")
    void testFindAll() {
        service.save(new Employee("Teste A", LocalDate.of(1991, 6, 18)));
        service.save(new Employee("Teste B", LocalDate.of(1991, 6, 19)));
        service.save(new Employee("Teste C", LocalDate.of(1991, 6, 20)));
        service.save(new Employee("Teste D", LocalDate.of(1991, 6, 21)));

        List<Employee> employees = service.findAll();

        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Deve ser possível buscar um funcionário pelo seu id")
    void testFindById() {
        Long id = service.save(new Employee("Teste A", LocalDate.of(1991, 6, 18))).getId();
        service.save(new Employee("Teste B", LocalDate.of(1991, 6, 19)));
        service.save(new Employee("Teste C", LocalDate.of(1991, 6, 20)));
        service.save(new Employee("Teste D", LocalDate.of(1991, 6, 21)));

        Employee employee = service.findBy(id);

        assertThat(employee).isNotNull();
        assertThat(employee.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Deve ser possível excluir um funcionário")
    @ExceptionHandler(RuntimeException.class)
    void testDelete() {
        Long id = service.save(new Employee("Teste A", LocalDate.of(1991, 6, 18))).getId();
        service.delete(id);
        
        RuntimeException exception =
                assertThrows(RuntimeException.class,
                   () -> service.findBy(id));
        
        
        String expectedMessage = "Funcionário inexistente";
        String actualMessage = exception.getMessage();
     
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
