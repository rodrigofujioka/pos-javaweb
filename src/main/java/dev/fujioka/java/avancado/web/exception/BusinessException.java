package dev.fujioka.java.avancado.web.exception;

public class BusinessException extends RuntimeException {


    public static final String ERROR_001 = "business.error.code.001";
    public static final String ERROR_002 = "business.error.code.002";
    private final String field;
    private final Object value;
    private final String message;

    public BusinessException(String field, Object value, String message) {
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