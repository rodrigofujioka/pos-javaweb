package dev.fujioka.eltonleite.infrastructure.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.fujioka.eltonleite.presentation.dto.shared.ResponseTO;

@Service
public class ResponseService {

    public <T> ResponseEntity<ResponseTO<T>> create(T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseTO<T>(data));
    }

    public <T> ResponseEntity<ResponseTO<T>> ok(T data) {
        return ResponseEntity.ok(new ResponseTO<T>(data));
    }

    public <T> ResponseEntity<T> accepted() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    public <T> ResponseEntity<T> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    public <T> ResponseEntity<T> noContent() {
        return ResponseEntity.noContent().build();
    }
}
