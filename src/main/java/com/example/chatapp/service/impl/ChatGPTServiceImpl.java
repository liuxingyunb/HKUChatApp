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
        return chatGPT.chat("假设你是一个正常人,请用相同的语言回答这段话，只需要输出回答的内容:"+question);
    }
    @Override
    public String translateQuestion(String question) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .apiHost("https://api.openai.com/") // 反向代理地址
                .build()
                .init();
        return chatGPT.chat("请翻译这句话为中文，只需要输出翻译后的内容:"+question);
    }
}
