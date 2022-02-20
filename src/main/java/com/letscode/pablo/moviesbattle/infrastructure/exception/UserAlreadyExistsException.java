package com.letscode.pablo.moviesbattle.infrastructure.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
