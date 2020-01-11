package dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.eltonleite.domain.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
