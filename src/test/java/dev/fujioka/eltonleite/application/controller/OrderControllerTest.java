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

import java.time.LocalDateTime;
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
import dev.fujioka.eltonleite.domain.model.order.Order;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.service.ResponseService;
import dev.fujioka.eltonleite.presentation.assembler.OrderAssembler;
import dev.fujioka.eltonleite.presentation.dto.order.OrderRequestTO;
import dev.fujioka.eltonleite.presentation.dto.order.OrderResponseTO;
import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;

@ActiveProfiles("test")
@ContextConfiguration(classes=WebApplication.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    
    
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private BaseService<Order> service;
    
    @MockBean
    private ResponseService responseService;
    
    @Test
    @DisplayName("acha todos os pedidos")
    public void testFindAll()
      throws Exception {
         
        Order orderOne = new Order(LocalDateTime.now(), 1L);
     
        List<Order> allOrders = Arrays.asList(orderOne);
        List<OrderResponseTO> data= OrderAssembler.from(allOrders);
        ResponseEntity<ResponseTO<List<OrderResponseTO>>> responseEntity = ResponseEntity.ok(new ResponseTO<List<OrderResponseTO>>(data));
     
        given(service.findAll()).willReturn(allOrders);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(get("/api/orders")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data", hasSize(1)))
          .andExpect(jsonPath("$.data[0].idUser", is(orderOne.getIdUser()), Long.class));
    }
    
    @Test
    @DisplayName("Acha um pedido pelo seu id")
    public void testFindById()
      throws Exception {
         
        Order orderOne = new Order(LocalDateTime.now(), 1L);
        orderOne.setId(1L);
     
        OrderResponseTO data= OrderAssembler.from(orderOne);
        ResponseEntity<ResponseTO<OrderResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<OrderResponseTO>(data));
     
        given(service.findBy(1L)).willReturn(orderOne);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(get("/api/orders/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.idUser", is(orderOne.getIdUser()),Long.class));
    }
    
    @Test
    @DisplayName("Salva um pedido")
    public void testSave()
      throws Exception {
         
        OrderRequestTO requestTO = new OrderRequestTO();
        requestTO.setDateOrder(LocalDateTime.now());
        requestTO.setIdUser(1L);
        
        Order order = OrderAssembler.from(requestTO);
        order.setId(1L);
        
        OrderResponseTO data= new OrderResponseTO(1L, LocalDateTime.now(), 1L);
        ResponseEntity<ResponseTO<OrderResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<OrderResponseTO>(data));
     
        given(service.save(OrderAssembler.from(requestTO))).willReturn(order);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(post("/api/orders")
          .contentType(MediaType.APPLICATION_JSON)
        .content("{\"dateOrder\":\"2020-01-19T12:50:51.977815\",\"idUser\":1}")
        )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.idUser", is(data.getIdUser()),Long.class));
    }
    
    @Test
    @DisplayName("Atualiza um pedido")
    public void testUpdate()
      throws Exception {
         
        OrderRequestTO requestTO = new OrderRequestTO();
        requestTO.setDateOrder(LocalDateTime.now());
        requestTO.setIdUser(1L);
        
        Order order = OrderAssembler.from(requestTO);
        order.setId(1L);
        
        OrderResponseTO data= new OrderResponseTO(1L, LocalDateTime.now(), 1L);
        ResponseEntity<ResponseTO<OrderResponseTO>> responseEntity = ResponseEntity.ok(new ResponseTO<OrderResponseTO>(data));
     
        given(service.update(1L,OrderAssembler.from(requestTO))).willReturn(order);
        given(responseService.ok(data)).willReturn(responseEntity);
        mvc.perform(put("/api/orders/1")
          .contentType(MediaType.APPLICATION_JSON)
        .content("{\"dateOrder\":\"2020-01-19T12:50:51.977815\",\"idUser\":1}")
        )
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.data.idUser", is(data.getIdUser()),Long.class));
    }
    
    @Test
    @DisplayName("Exclui um pedido")
    public void testDelete()
      throws Exception {
         
        mvc.perform(delete("/api/orders/1")
        )
          .andExpect(status().isNoContent());
    }
    
}
