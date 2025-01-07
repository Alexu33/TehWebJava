package com.IstrateCristianAlexandru408.onlineshop.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
