package com.example.chatapp.service;

import com.example.chatapp.model.po.Personal_chat;

import java.util.List;
import java.util.Map;

public interface Personal_chatService {
    Personal_chat getPersonal_chatById(int id);
    void addPersonal_chat(Personal_chat personal_chat);
    void updatePersonal_chat(Map<String, Object> map);
    void deletePersonal_chat(int id);

    List<Personal_chat> showHistory(int senderId, int receiverId);
    List<Personal_chat> showHistoryByPage(int senderId, int receiverId, int offset, int pageSize);
    int getChatSum(int senderId, int receiverId);
}
