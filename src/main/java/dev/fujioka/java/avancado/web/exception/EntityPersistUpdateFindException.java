package dev.fujioka.java.avancado.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityPersistUpdateFindException extends RuntimeException {

    private final String field;
    private final Object value;
    private final String message;

    public EntityPersistUpdateFindException(String field, Object value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}