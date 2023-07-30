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
        return chatGPT.chat("假设你是一个人类,请用相同的语言回答这段话，只需要输出回答的内容:"+question);
    }
    @Override
    public String translateQuestion(String question) {
        ChatGPT chatGPT = ChatGPT.builder()
                .apiKey("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")
                .apiHost("https://api.openai.com/") // 反向代理地址
                .build()
                .init();
        return chatGPT.chat("如果这句话是中文，请翻译成英语；如果这句话是别的语言，请翻译为中文，只需要输出翻译后的内容:"+question);
    }
}
