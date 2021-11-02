package com.rishabh.lendingengine.domain.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("User with usename: "+username+" not found.");
    }
}
