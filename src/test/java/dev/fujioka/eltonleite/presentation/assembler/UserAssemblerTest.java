package dev.fujioka.eltonleite.presentation.assembler;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.eltonleite.domain.model.user.User;
import dev.fujioka.eltonleite.presentation.dto.user.UserRequestTO;
import dev.fujioka.eltonleite.presentation.dto.user.UserResponseTO;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Assembler: Usuários")
class UserAssemblerTest {


    @Test
    @DisplayName("Deve converter um usuário em um DTO de resposta")
    void testEntityToResponseTO() {
         User entity = new  User("Teste 1", "Teste 1", LocalDate.now());
        entity.setId(1L);
         UserResponseTO responseTO =  UserAssembler.from(entity);

        assertThat(responseTO).isNotNull();
        assertThat(responseTO.getId()).isNotNull();
        assertThat(responseTO.getUsername()).isEqualTo(entity.getUsername());
        assertThat(responseTO.getDateBirth()).isEqualTo(entity.getDateBirth());
    }
    
    @Test
    @DisplayName("Deve converter uma requisição em um usuário")
    void testRequestToEntity() {
         UserRequestTO requestTO = new  UserRequestTO();
        requestTO.setUsername("Teste 1");
        requestTO.setPassword("Teste 1");
        requestTO.setDateBirth(LocalDate.now());
         User entity =  UserAssembler.from(requestTO);

        assertThat(entity).isNotNull();
        assertThat(entity.getUsername()).isEqualTo(requestTO.getUsername());
        assertThat(entity.getPassword()).isEqualTo(requestTO.getPassword());
        assertThat(entity.getDateBirth()).isEqualTo(requestTO.getDateBirth());
    }

}
