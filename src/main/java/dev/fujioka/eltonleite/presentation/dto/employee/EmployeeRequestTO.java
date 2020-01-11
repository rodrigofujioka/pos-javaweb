package dev.fujioka.eltonleite.presentation.dto.employee;

import java.time.LocalDate;

public class EmployeeRequestTO {

    private String name;

    private LocalDate dateBirth;

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
        return String.format("Employee [name=%s, dateBirth=%s]", name, dateBirth);
    }

}
