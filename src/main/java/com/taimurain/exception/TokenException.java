package com.taimurain.exception;

public class TokenException extends TaimurainApplicationException {
    public TokenException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
