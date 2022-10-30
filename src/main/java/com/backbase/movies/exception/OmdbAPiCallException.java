package com.backbase.movies.exception;

public class OmdbAPiCallException extends RuntimeException {
    public OmdbAPiCallException(String message) {
        super(message);
    }
}
