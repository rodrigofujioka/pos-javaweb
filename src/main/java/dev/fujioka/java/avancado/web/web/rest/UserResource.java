package dev.fujioka.java.avancado.web.web.rest;

import dev.fujioka.java.avancado.web.domain.User;
import dev.fujioka.java.avancado.web.exception.EntityPersistUpdateFindException;
import dev.fujioka.java.avancado.web.service.UserService;
import dev.fujioka.java.avancado.web.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(Long id) {
        UserDto dto =new UserDto(userService.findById(id)
                .orElseThrow(() -> new EntityPersistUpdateFindException("Person", "id", id.toString())));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto>
    save(@Valid @RequestBody User user) {
        UserDto userDto = new UserDto(userService.save(user)
                .orElseThrow(() -> new EntityPersistUpdateFindException("Person", "Not save", user.toString())));
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/user")
    public ResponseEntity<UserDto> update(@Valid @RequestBody User user) {
        if(user.getId()!=null){
            UserDto userDto = new UserDto(userService.save(user)
                .orElseThrow(() -> new EntityPersistUpdateFindException("Person", "Not Update", user.toString())));
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/user")
    public ResponseEntity delete(@Valid @RequestBody User user) {
        userService.delete(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        User userDelete = userService.findById(id)
                .orElseThrow(() -> new EntityPersistUpdateFindException("Person", "id", id.toString()));
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
