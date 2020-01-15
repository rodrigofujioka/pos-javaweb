package dev.fujioka.eltonleite.presentation.assembler;

import java.util.List;
import java.util.stream.Collectors;

import dev.fujioka.eltonleite.domain.model.order.Order;
import dev.fujioka.eltonleite.presentation.dto.order.OrderRequestTO;
import dev.fujioka.eltonleite.presentation.dto.order.OrderResponseTO;

public final class OrderAssembler {
    
    private OrderAssembler() {
    }
    
    public static Order from(OrderRequestTO requestTO) {
        return new Order(requestTO.getDateOrder(), requestTO.getIdUser());
    }
    
    public static OrderResponseTO from(Order order) {
        return new OrderResponseTO(order.getId(), order.getDateOrder(), order.getIdUser());
    }
    
    public static List<OrderResponseTO> from(List<Order> orders) {
        return orders.stream().map(u -> from(u)).collect(Collectors.toList());
    }

}
