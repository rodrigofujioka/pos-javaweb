package dev.fujioka.eltonleite.presentation.assembler.user;

import java.util.List;
import java.util.stream.Collectors;

import dev.fujioka.eltonleite.domain.model.user.User;
import dev.fujioka.eltonleite.presentation.dto.user.UserRequestTO;
import dev.fujioka.eltonleite.presentation.dto.user.UserResponseTO;

public final class UserAssembler {
    
    private UserAssembler() {
    }
    
    public static User from(UserRequestTO requestTO) {
        return new User(requestTO.getUsername(), requestTO.getPassword(), requestTO.getDateBirth());
    }
    
    public static UserResponseTO from(User user) {
        return new UserResponseTO(user.getId(), user.getUsername(), user.getDateBirth());
    }
    
    public static List<UserResponseTO> from(List<User> users) {
        return users.stream().map(u -> from(u)).collect(Collectors.toList());
    }

}
