package dev.fujioka.fagnerlima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.fagnerlima.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
