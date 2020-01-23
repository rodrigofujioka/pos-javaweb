package dev.fujioka.fagnerlima.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.fujioka.fagnerlima.domain.User;
import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public User getUser(@PathVariable Long id) {

        return userService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
    }

    @PostMapping("/user")
    public ResponseEntity<User>
    save(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/user")
    public ResponseEntity update(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping("/user")
    public ResponseEntity<String> delete(@Valid @RequestBody User user) {
        userService.delete(user);
       return  ResponseEntity.ok().body("User excluded ID: " + user.getId());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        User userDelete = userService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
        userService.deleteById(id);
        return ResponseEntity.ok().body("User excluded ID: " + id);
    }


}
