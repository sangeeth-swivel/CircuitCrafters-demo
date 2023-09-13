package com.taimurain.exception;

public class TaimurainApplicationException extends RuntimeException{
    public TaimurainApplicationException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
    public TaimurainApplicationException(String errorMessage) {
        super(errorMessage);
    }
}
