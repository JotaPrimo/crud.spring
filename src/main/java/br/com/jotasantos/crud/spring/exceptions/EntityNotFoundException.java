package br.com.jotasantos.crud.spring.exceptions;

public class EntityNotFoundException  extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
