package com.example.chatapp.service;

import com.example.chatapp.model.po.User;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String user_name);
    void addUser(User user);
    void updateUser(Map<String, Object> map);
    void deleteUser(int id);
}
