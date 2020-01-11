package dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.eltonleite.domain.model.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
