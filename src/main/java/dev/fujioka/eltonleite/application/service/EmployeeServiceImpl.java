package dev.fujioka.eltonleite.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.eltonleite.domain.model.employee.Employee;
import dev.fujioka.eltonleite.domain.service.BaseService;
import dev.fujioka.eltonleite.infrastructure.persistence.hibernate.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements BaseService<Employee> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee save(Employee entity) {

        return repository.save(entity);
    }

    @Override
    public Employee update(Long id, Employee entity) {
        Employee savedEmployee = findBy(id);
        savedEmployee.setName(entity.getName());
        savedEmployee.setDateBirth(entity.getDateBirth());
        return repository.save(savedEmployee);
    }

    @Override
    public Employee findBy(Long id) {
        Optional<Employee> optEmployee = repository.findById(id);
        if(!optEmployee.isPresent()) {
            throw new RuntimeException("Funcionario inexistente");
        }
        return optEmployee.get();
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        
    }

}
