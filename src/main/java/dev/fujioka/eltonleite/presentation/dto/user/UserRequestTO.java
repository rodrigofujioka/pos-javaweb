package dev.fujioka.eltonleite.presentation.dto.user;

import java.time.LocalDate;

public class UserRequestTO {

    private String username;

    private String password;

    private LocalDate dateBirth;

    public UserRequestTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return String.format("UserRequest [username=%s, password=%s, dateBirth=%s]", username, password, dateBirth);
    }

}
