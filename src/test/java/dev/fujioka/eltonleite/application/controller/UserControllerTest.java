package dev.fujioka.eltonleite.application.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import dev.fujioka.eltonleite.WebApplication;
import dev.fujioka.eltonleite.domain.model.user.User;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.UserAssembler;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;
import dev.fujioka.eltonleite.presentation.dto.user.UserRequestTO;
import dev.fujioka.eltonleite.presentation.dto.user.UserResponseTO;

@ActiveProfiles("test")
//@SpringBootTest
@ContextConfiguration(classes=WebApplication.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private BaseService<User> service;
    
    @MockBean
    private ResponseService responseService;
    
    @Test
    @DisplayName("acha todos os usuários")
    public void testFindAll()
      throws Exception {
         
        User alex = new User("alex", "mamae123", LocalDate.now());
     
        List<User> allUsers = Arrays.asList(alex);
        List<UserResponseTO> data= UserAssembler.from(allUsers);
        ResponseEntity<ResponseTO<List<UserResponseTO>>> responseEntity = ResponseEntity.ok(new ResponseTO<List<UserResponseTO>>(data));
     
        given(service.findAll()).willReturn(allUsers);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(get("/api/users")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data", hasSize(1)))
          .andExpect(jsonPath("$.data[0].username", is(alex.getUsername())));
    }
    
    @Test
    @DisplayName("Acha um usuário pelo seu id")
    public void testFindById()
      throws Exception {
         
        User alex = new User("alex", "mamae123", LocalDate.now());
     
        UserResponseTO data= UserAssembler.from(alex);
        ResponseEntity<ResponseTO<UserResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<UserResponseTO>(data));
     
        given(service.findBy(1L)).willReturn(alex);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(get("/api/users/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.username", is(alex.getUsername())));
    }
    
    @Test
    @DisplayName("Salva um usuário")
    public void testSave()
      throws Exception {
         
        UserRequestTO requestTO = new UserRequestTO();
        requestTO.setUsername("alex");
        requestTO.setPassword("mamae123");
        requestTO.setDateBirth(LocalDate.now());
        
        User user = UserAssembler.from(requestTO);
        user.setId(1L);
        
        UserResponseTO data= new UserResponseTO(1L, "alex", LocalDate.now());
        ResponseEntity<ResponseTO<UserResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<UserResponseTO>(data));
     
        given(service.save(UserAssembler.from(requestTO))).willReturn(user);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(post("/api/users")
          .contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"alex\",\"password\":\"mamae123\",\"dateBirth\": \"2020-01-17\"}")
        )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.username", is(data.getUsername())));
    }
    
    @Test
    @DisplayName("Atualiza um usuário")
    public void testUpdate()
      throws Exception {
         
        UserRequestTO requestTO = new UserRequestTO();
        requestTO.setUsername("alex");
        requestTO.setPassword("mamae123");
        requestTO.setDateBirth(LocalDate.now());
        
        User user = UserAssembler.from(requestTO);
        user.setId(1L);
        
        UserResponseTO data= new UserResponseTO(1L, "alex", LocalDate.now());
        ResponseEntity<ResponseTO<UserResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<UserResponseTO>(data));
     
        given(service.update(1L,UserAssembler.from(requestTO))).willReturn(user);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(put("/api/users/1")
          .contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"alex\",\"password\":\"mamae123\",\"dateBirth\": \"2020-01-17\"}")
        )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.username", is(data.getUsername())));
    }
    
    @Test
    @DisplayName("Exclui um usuário")
    public void testDelete()
      throws Exception {
         
        mvc.perform(delete("/api/users/1")
        )
          .andExpect(status().isNoContent());
    }
    
}
