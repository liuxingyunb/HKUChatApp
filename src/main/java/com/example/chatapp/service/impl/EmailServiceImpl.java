package com.example.chatapp.service.impl;

import com.example.chatapp.model.po.User;
import com.example.chatapp.service.EmailService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    private Map<String, String> verificationCodeMap = new HashMap<>();

    public void sendVerificationEmail(String to, String verificationCode) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("Random_Chatapp@outlook.com");
        message.setSubject("Account Verification");
        message.setText("Your verification code is: " + verificationCode);
        mailSender.send(message);
    }

    @Override
    public String generateVerificationCode() {
        // 设置验证码的位数
        int codeLength = 6;

        // 可选的验证码字符
        String characters = "0123456789";

        // 生成随机验证码
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    // 保存验证码和用户信息到内存
    public void saveVerificationCode(String username, String verificationCode) {
        verificationCodeMap.clear();
        verificationCodeMap.put(username, verificationCode);
    }

    public Map<String, String> getVerificationCodeMap() {
        return verificationCodeMap;
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    @Override
    public void findPassword(String username){
        User user=userService.getUserByUsername(username);
        String to=user.getMail();
        String password=user.getPassword();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("Random_Chatapp@outlook.com");
        message.setSubject("Recover Password");
        message.setText("Your password is: " + password + "\nFor security reasons, please change your password in time after logging in!");
        mailSender.send(message);
    }
}

