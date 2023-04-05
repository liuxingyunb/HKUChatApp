package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Group_chatDao;
import com.example.chatapp.model.po.Group_chat;
import com.example.chatapp.service.Group_chatService;
import com.example.chatapp.service.Group_chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Group_chatServiceImpl implements Group_chatService {
    @Autowired
    private Group_chatDao group_chatDao;
    @Override
    public Group_chat getGroup_chatById(int id) {
        return group_chatDao.selectGroup_chatById(id);
    }

    @Override
    public void addGroup_chat(Group_chat Group_chat) {
        group_chatDao.insertGroup_chat(Group_chat);
    }

    @Override
    public void updateGroup_chat(Map<String, Object> map) {
        group_chatDao.updateGroup_chat(map);
    }

    @Override
    public void deleteGroup_chat(int id) {
        group_chatDao.deleteGroup_chat(id);
    }
}
