package com.example.chatapp;

import com.example.chatapp.utils.ChatUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("com.example.chatapp.*")
@ServletComponentScan("com.example.chatapp.*")
public class ChatAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatAppApplication.class, args);
    }
    @PostConstruct
    public void init() throws Exception{
        ChatUtil.startChatStatisticsTask();
    }
}
