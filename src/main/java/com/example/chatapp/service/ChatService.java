package com.example.chatapp.service;

import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;

import java.util.List;

public interface ChatService {

    public List<User> contactPersonal(int userId);

    public List<Chat_group> contactGroup(int userId);
    public List<User> recommendPersonal(int userId,int num);
    public User recommendPersonal_tag(int userId);
    public List<Chat_group> recommendGroup(int userId,int num);
}
