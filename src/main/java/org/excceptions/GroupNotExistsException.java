package org.excceptions;

public class GroupNotExistsException extends RuntimeException {
    public GroupNotExistsException(String message) {
        super(message);
    }
}
