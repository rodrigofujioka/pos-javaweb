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

import dev.fujioka.eltonleite.domain.model.user.User;
import dev.fujioka.eltonleite.domain.service.BaseService;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private BaseService<User> service;

    @Test
    @DisplayName("Testa a criação de usuários")
    void testSave() {
        User user = new User("Teste 1", "Teste 1", LocalDate.of(1991, 6, 18));
        User userSaved = service.save(user);

        assertThat(userSaved).isNotNull();
        assertThat(userSaved.getId()).isNotNull();
        assertThat(userSaved.getUsername()).isEqualTo(user.getUsername());
        assertThat(userSaved.getPassword()).isEqualTo(user.getPassword());
        assertThat(userSaved.getDateBirth()).isEqualTo(user.getDateBirth());
    }

    @Test
    @DisplayName("Testa a atualização de usuários")
    void testUpdate() {
        User userSaved = service.save(new User("Teste A", "Teste A", LocalDate.of(1991, 6, 18)));

        User userUpdated = service.update(userSaved.getId(), new User("Teste U", "Teste U", LocalDate.of(1995, 6, 18)));

        assertThat(userUpdated).isNotNull();
        assertThat(userSaved.getId()).isEqualTo(userSaved.getId());
        assertThat(userUpdated.getUsername()).isNotEqualTo(userSaved.getUsername());
        assertThat(userUpdated.getPassword()).isNotEqualTo(userSaved.getPassword());
        assertThat(userUpdated.getDateBirth()).isNotEqualTo(userSaved.getDateBirth());
    }

    @Test
    @DisplayName("Testa a listagem de usuários")
    void testFindAll() {
        service.save(new User("Teste A", "Teste A", LocalDate.of(1991, 6, 18)));
        service.save(new User("Teste B", "Teste B", LocalDate.of(1991, 6, 19)));
        service.save(new User("Teste C", "Teste C", LocalDate.of(1991, 6, 20)));
        service.save(new User("Teste D", "Teste D", LocalDate.of(1991, 6, 21)));

        List<User> users = service.findAll();

        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Testa a busca de um usuário")
    void testFindById() {
        Long id = service.save(new User("Teste A", "Teste A", LocalDate.of(1991, 6, 18))).getId();
        service.save(new User("Teste B", "Teste B", LocalDate.of(1991, 6, 19)));
        service.save(new User("Teste C", "Teste C", LocalDate.of(1991, 6, 20)));
        service.save(new User("Teste D", "Teste D", LocalDate.of(1991, 6, 21)));

        User user = service.findBy(id);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Testa a exclusão de um usuário")
    @ExceptionHandler(RuntimeException.class)
    void testDelete() {
        Long id = service.save(new User("Teste A", "Teste A", LocalDate.of(1991, 6, 18))).getId();
        service.delete(id);
        
        RuntimeException exception =
                assertThrows(RuntimeException.class,
                   () -> service.findBy(id));
        
        
        String expectedMessage = "Usuário inexistente";
        String actualMessage = exception.getMessage();
     
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}
