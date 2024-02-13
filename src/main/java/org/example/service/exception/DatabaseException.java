package org.example.service.exception;

import java.io.IOException;

public class DatabaseException extends IOException {
    public DatabaseException(String message) {
        super(message);
    }
}
