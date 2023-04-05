package com.example.chatapp.service;

import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.po.Photo_wall;

import java.util.Map;

public interface Personal_chatService {
    Personal_chat getPersonal_chatById(int id);
    void addPersonal_chat(Personal_chat personal_chat);
    void updatePersonal_chat(Map<String, Object> map);
    void deletePersonal_chat(int id);
}
