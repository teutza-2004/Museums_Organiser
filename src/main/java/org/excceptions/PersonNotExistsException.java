package org.excceptions;

public class PersonNotExistsException extends RuntimeException {
    public PersonNotExistsException(String message) {
        super(message);
    }
}
