package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Chat_groupDao;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.exception.ChatGroupNotFoundException;
import com.example.chatapp.exception.UserAlreadyExistsException;
import com.example.chatapp.exception.UserNotFoundException;
import com.example.chatapp.exception.UserNotInChatGroupException;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Chat_groupServiceImpl implements Chat_groupService {
    @Autowired
    private Chat_groupDao chat_groupDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Chat_group getChat_groupById(int id) {
        return chat_groupDao.selectChat_groupById(id);
    }

    @Override
    public void addChat_group(Chat_group chat_group) {
        chat_groupDao.addChat_group(chat_group);
    }

    @Override
    public void updateChat_group(Map<String, Object> map) {
        chat_groupDao.updateChat_group(map);
    }

    @Override
    public void deleteChat_group(int id) {
        chat_groupDao.deleteChat_group(id);
    }
    /**
     * 将用户添加到指定群组中的成员列表中
     *
     * @param groupId  群组ID
     * @param username 用户名
     * @throws UserNotFoundException   如果用户不存在
     * @throws ChatGroupNotFoundException 如果群组不存在
     * @throws UserAlreadyExistsException 如果用户已经存在于群组中
     */
    public void addUserToChatGroup(int groupId, String username)
            throws UserNotFoundException, ChatGroupNotFoundException, UserAlreadyExistsException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }

        // 检查群组是否存在
        Chat_group chatGroup = chat_groupDao.selectChat_groupById(groupId);
        if (chatGroup == null) {
            throw new ChatGroupNotFoundException("Chat group not found: " + groupId);
        }

        // 检查用户是否已经存在于群组中
        if (chatGroup.getMembers() != null && chatGroup.getMembers().contains(username)) {
            throw new UserAlreadyExistsException("User already exists in chat group: " + username);
        }

        // 将用户添加到群组中
        Map<String,Object> m = new HashMap<>();
        m.put("groupId",groupId);
        m.put("username",username);
        chat_groupDao.addUserToGroup(m);
    }

    /**
     * 从指定的群组中删除用户
     *
     * @param groupId  群组ID
     * @param username 用户名
     * @throws UserNotFoundException   如果用户不存在
     * @throws ChatGroupNotFoundException 如果群组不存在
     * @throws UserNotInChatGroupException 如果用户不在群组中
     */
    public void removeUserFromChatGroup(int groupId, String username)
            throws UserNotFoundException, ChatGroupNotFoundException, UserNotInChatGroupException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }

        // 检查群组是否存在
        Chat_group chatGroup = chat_groupDao.selectChat_groupById(groupId);
        if (chatGroup == null) {
            throw new ChatGroupNotFoundException("Chat group not found: " + groupId);
        }

        // 检查用户是否在群组中
        if (chatGroup.getMembers() == null || !chatGroup.getMembers().contains(username)) {
            throw new UserNotInChatGroupException("User not in chat group: " + username);
        }

        // 从群组中删除用户
        Map<String,Object> m = new HashMap<>();
        m.put("groupId",groupId);
        m.put("username",username);
        chat_groupDao.addUserToGroup(m);
    }
}
