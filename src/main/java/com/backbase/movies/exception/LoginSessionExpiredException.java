package com.backbase.movies.exception;

public class LoginSessionExpiredException extends RuntimeException {
    public LoginSessionExpiredException(String s) {
        super(s);
    }
}
