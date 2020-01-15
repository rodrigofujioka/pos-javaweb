package dev.fujioka.eltonleite.presentation.dto.employee;

import java.time.LocalDate;

public class EmployeeResponseTO {

    private Long id;

    private String name;

    private LocalDate dateBirth;

    public EmployeeResponseTO(Long id, String name, LocalDate dateBirth) {
        super();
        this.id = id;
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    @Override
    public String toString() {
        return String.format("Employee [id=%s, name=%s, dateBirth=%s]", id, name, dateBirth);
    }

}
