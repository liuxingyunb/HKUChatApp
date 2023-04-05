package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Chat_groupDao;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Chat_groupServiceImpl implements Chat_groupService {
    @Autowired
    private Chat_groupDao chat_groupDao;

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
}
