package com.example.chatapp.exception;

public class ChatGroupNotFoundException extends RuntimeException {
    public ChatGroupNotFoundException(String message) {
        super(message);
    }
}
