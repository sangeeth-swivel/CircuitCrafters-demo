package com.taimurain.exception;

public class UserExistException extends TaimurainApplicationException {

    public UserExistException(String errorMessage) {
        super(errorMessage);
    }
}
