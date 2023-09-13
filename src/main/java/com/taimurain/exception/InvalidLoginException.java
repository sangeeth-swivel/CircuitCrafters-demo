package com.taimurain.exception;

public class InvalidLoginException extends TaimurainApplicationException {
    public InvalidLoginException(String errorMessage) {
        super(errorMessage);
    }
}
