package dev.fujioka.fagnerlima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.fujioka.fagnerlima.domain.User;
import dev.fujioka.fagnerlima.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
    	
        return userRepository.findAll();
        
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.of(userRepository.save(entity));
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

}
