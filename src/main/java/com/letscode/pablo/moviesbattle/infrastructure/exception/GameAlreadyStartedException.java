package com.letscode.pablo.moviesbattle.infrastructure.exception;

public class GameAlreadyStartedException extends Exception {

    public GameAlreadyStartedException(String message) {
        super(message);
    }
}
