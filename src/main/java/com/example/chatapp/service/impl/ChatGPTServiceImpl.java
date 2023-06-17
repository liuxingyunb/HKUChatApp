package com.example.chatapp.service.impl;

import com.example.chatapp.service.ChatGPTService;
import com.plexpt.chatgpt.ChatGPT;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Override
    public String computeQuestion(String question) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .apiHost("https://api.openai.com/") // 反向代理地址
                .build()
                .init();
        return chatGPT.chat(question);
    }
}
