package com.example.chatapp.service;

import com.example.chatapp.exception.ChatGroupNotFoundException;
import com.example.chatapp.exception.UserAlreadyExistsException;
import com.example.chatapp.exception.UserNotFoundException;
import com.example.chatapp.exception.UserNotInChatGroupException;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(int id);
    User getUserByUsername(String user_name);
    void addUser(User user);
    void updateUser(Map<String, Object> map);
    void deleteUser(int id);
    List<User> getUser(Map<String, Object> map);
    void addUserToUser(int id, String username) throws UserNotFoundException, UserAlreadyExistsException;
    public void removeUserFromUser(int id, String username) throws UserNotFoundException, UserNotInChatGroupException;
    public List<User> selectFriends(int id);
    public List<Chat_group> selectChatGroupsContainUser(String username);
    public List<User> selectAllUsers();
    void addFriendToUser(int userId, String friendName) throws UserNotFoundException, UserAlreadyExistsException;
}
