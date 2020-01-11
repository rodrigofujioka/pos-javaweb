package dev.fujioka.eltonleite.presentation.dto.user;

import java.time.LocalDate;

public class UserResponseTO {

    private Long id;

    private String username;

    private LocalDate dateBirth;

    public UserResponseTO(Long id, String username, LocalDate dateBirth) {
        super();
        this.id = id;
        this.username = username;
        this.dateBirth = dateBirth;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

}
