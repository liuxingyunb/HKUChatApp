package com.example.chatapp.dao;

import com.example.chatapp.model.po.Personal_chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Personal_chatdao {
    @Insert("insert into personal_chat(id, sender_id, receiver_id, message_type, content, send_time) value(#{id}, #{sender_id}, #{receiver_id}, #{message_type}, #{content}, #{send_time})")
    public void addPersonal_chat(Personal_chat personalChat);

}
