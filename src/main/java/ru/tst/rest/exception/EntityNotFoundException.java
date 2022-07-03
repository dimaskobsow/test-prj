package ru.tst.rest.exception;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends NoSuchElementException {
    public EntityNotFoundException(Class c, String id) {
        super("Entity [%s = %s] not found".formatted(c.getName(), id));
    }

    public EntityNotFoundException(Class c, Long id) {

        this(c, String.valueOf(id));
    }
}
