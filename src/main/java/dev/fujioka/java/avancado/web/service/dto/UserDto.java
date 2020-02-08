package dev.fujioka.java.avancado.web.service.dto;

import dev.fujioka.java.avancado.web.domain.User;

import java.util.Date;
import java.util.Objects;

public class UserDto {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private Date dtCreation;
    private Date dtUpdate;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dtCreation = user.getDtCreation();
        this.dtUpdate = user.getDtUpdate();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDtCreation() {
        return dtCreation;
    }

    public void setDtCreation(Date dtCreation) {
        this.dtCreation = dtCreation;
    }

    public Date getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Date dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(dtCreation, userDto.dtCreation) &&
                Objects.equals(dtUpdate, userDto.dtUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dtCreation, dtUpdate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dtCreation=" + dtCreation +
                ", dtUpdate=" + dtUpdate +
                '}';
    }
}
