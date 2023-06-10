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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
        if (chatGroup.getMembers() != null&&chatGroup.getMembers() != "" ) {
            boolean flag = true;
            String[] tmp = chatGroup.getMembers().split(";");
            for(String i: tmp){
                if(Integer.parseInt(i)==user.getId())flag=false;
            }
            if(!flag)throw new UserAlreadyExistsException("User already exists in chat group: " + user);
        }
        // 将用户添加到群组中
        Map<String,Object> m = new HashMap<>();
        m.put("groupId",groupId);
        m.put("username",String.valueOf(user.getId()));
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

    @Override
    public List<User> selectMembers(int id) {
        String members = chat_groupDao.selectChat_groupById(id).getMembers();
        members = members.replaceAll(";$", "");
        String[] names = members.split(";");
        List<User> memberList = new LinkedList<>();
        for (String name : names){
            memberList.add(userDao.selectUserByUsername(name));
        }
        return memberList;
    }

    @Override
    public List<Chat_group> selectAllGroups() {
        Map<String,Object> map = new HashMap<>();
        return chat_groupDao.selectChatGroup(map);
    }
    /**
     * 将用户添加到指定群组中的成员列表中
     *
     * @param groupId  群组ID
     * @param memberName 用户名
     * @throws UserNotFoundException   如果用户不存在
     * @throws UserAlreadyExistsException 如果用户已经存在于群组中
     */
    public void addMemberToChatGroup(int groupId, String memberName)
            throws UserNotFoundException, UserAlreadyExistsException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(memberName);
        String members = chat_groupDao.selectChat_groupById(groupId).getMembers();
        if (user == null) {
            throw new UserNotFoundException("User not found: " + memberName);
        }

        // 检查群组是否存在
        Chat_group chatGroup = chat_groupDao.selectChat_groupById(groupId);
        if (chatGroup == null) {
            throw new ChatGroupNotFoundException("Chat group not found: " + groupId);
        }

        // 检查用户是否已经存在于群组中
        if (members != null &&!members.equals("")) {
            boolean flag = true;
            String[] tmp = members.split(";");
            for(String i: tmp){
                if(Integer.parseInt(i)==user.getId())flag=false;
            }
            if(!flag)throw new UserAlreadyExistsException("User already exists in chat group: " + memberName);
        }

        members += String.valueOf(user.getId()) + ';';
        Map<String,Object> m = new HashMap<>();
        m.put("id",groupId);
        m.put("members",members);
        chat_groupDao.updateChat_group(m);
    }
    @Override
    public void removeMemberIdFromGroup(int groupId, String userName)
            throws UserNotFoundException, UserNotInChatGroupException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(userName);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + userName);
        }

        Chat_group chatGroup = chat_groupDao.selectChat_groupById(groupId);
        if (chatGroup == null) {
            throw new ChatGroupNotFoundException("Chat group not found: " + groupId);
        }

        int userId = user.getId();
        if (chatGroup.getMembers() == null || !chatGroup.getMembers().contains(String.valueOf(userId))) {
            throw new UserNotInChatGroupException("User not in group: " + user.getUsername());
        }

        // 从群组中删除用户
        Map<String,Object> m = new HashMap<>();
        m.put("groupId",groupId);
        m.put("userId",userId);
        chat_groupDao.removeUserFromGroup(m);
    }
}
