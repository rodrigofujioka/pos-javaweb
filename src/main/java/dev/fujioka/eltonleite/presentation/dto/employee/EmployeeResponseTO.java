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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return String.format("Employee [id=%s, name=%s, dateBirth=%s]", id, name, dateBirth);
    }

}
