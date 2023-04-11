package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Personal_chatDao;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.Personal_chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Personal_chatServiceImpl implements Personal_chatService {
    @Autowired
    private Personal_chatDao personal_chatDao;
    @Override
    public Personal_chat getPersonal_chatById(int id) {
        return personal_chatDao.selectPersonal_chatsById(id);
    }

    @Override
    public void addPersonal_chat(Personal_chat personal_chat) {
        personal_chatDao.addPersonal_chat(personal_chat);
    }

    @Override
    public void updatePersonal_chat(Map<String, Object> map) {
        personal_chatDao.updatePersonal_chat(map);
    }

    @Override
    public void deletePersonal_chat(int id) {
        personal_chatDao.deletePersonal_chatById(id);
    }

}
