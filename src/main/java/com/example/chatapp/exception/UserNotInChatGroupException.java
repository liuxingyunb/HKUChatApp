package com.example.chatapp.exception;

public class UserNotInChatGroupException extends RuntimeException {
    public UserNotInChatGroupException(String message) {
        super(message);
    }
}