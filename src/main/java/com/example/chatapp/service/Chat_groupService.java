package com.example.chatapp.service;

import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;

import java.util.Map;

public interface Chat_groupService {
    Chat_group getChat_groupById(int id);
    void addChat_group(Chat_group chat_group);
    void updateChat_group(Map<String, Object> map);
    void deleteChat_group(int id);
}
