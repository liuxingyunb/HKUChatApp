package com.example.chatapp.service;

import com.example.chatapp.exception.ChatGroupNotFoundException;
import com.example.chatapp.exception.UserAlreadyExistsException;
import com.example.chatapp.exception.UserNotFoundException;
import com.example.chatapp.exception.UserNotInChatGroupException;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;

import java.util.List;
import java.util.Map;

public interface Chat_groupService {
    Chat_group getChat_groupById(int id);
    void addChat_group(Chat_group chat_group);
    void updateChat_group(Map<String, Object> map);
    void deleteChat_group(int id);
    void addUserToChatGroup(int groupId, String username) throws UserNotFoundException, ChatGroupNotFoundException, UserAlreadyExistsException;
    public void removeUserFromChatGroup(int groupId, String username) throws UserNotFoundException, ChatGroupNotFoundException, UserNotInChatGroupException;
    public List<User> selectMembers(int id);
    public List<Chat_group> selectAllGroups();

}
