package dev.fujioka.eltonleite.presentation.assembler;

import java.util.List;
import java.util.stream.Collectors;

import dev.fujioka.eltonleite.domain.model.employee.Employee;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeRequestTO;
import dev.fujioka.eltonleite.presentation.dto.employee.EmployeeResponseTO;

public final class EmployeeAssembler {
    
    private EmployeeAssembler() {
    }
    
    public static Employee from(EmployeeRequestTO requestTO) {
        return new Employee(requestTO.getName(), requestTO.getDateBirth());
    }
    
    public static EmployeeResponseTO from(Employee employee) {
        return new EmployeeResponseTO(employee.getId(), employee.getName(), employee.getDateBirth());
    }
    
    public static List<EmployeeResponseTO> from(List<Employee> employees) {
        return employees.stream().map(EmployeeAssembler::from).collect(Collectors.toList());
    }

}
