package com.example.chatapp.service;

import java.util.Map;

public interface EmailService {
    void sendVerificationEmail(String to, String verificationCode);
    String generateVerificationCode();
    void saveVerificationCode(String username, String verificationCode);
    Map<String, String> getVerificationCodeMap();
    boolean isValidEmail(String email);
}
