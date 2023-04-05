package com.example.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class ChatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
    }

}
