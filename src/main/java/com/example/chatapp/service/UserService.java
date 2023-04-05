package com.example.chatapp.service;

import com.example.chatapp.model.po.User;

import java.util.Map;

public interface UserService {
    User getUserById(int id);
    void addUser(User user);
    void updateUser(Map<String, Object> map);
    void deleteUser(int id);
}
