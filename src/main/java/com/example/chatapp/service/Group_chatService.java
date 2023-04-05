package com.example.chatapp.service;

import com.example.chatapp.model.po.Group_chat;
import com.example.chatapp.model.po.User;

import java.util.Map;

public interface Group_chatService {
    Group_chat getGroup_chatById(int id);
    void addGroup_chat(Group_chat group_chat);
    void updateGroup_chat(Map<String, Object> map);
    void deleteGroup_chat(int id);
}
