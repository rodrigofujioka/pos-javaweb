package dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.eltonleite.domain.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
