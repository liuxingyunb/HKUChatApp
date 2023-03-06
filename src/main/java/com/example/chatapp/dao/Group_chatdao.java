package com.example.chatapp.dao;

import com.example.chatapp.model.po.Group_chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Group_chatdao {
    @Insert("insert into group_chat(id, sender_id, group_id, message_type, content, send_time) value(#{id}, #{sender_id}, #{group_id}, #{message_type}, #{content}, #{send_time})")
    public void addChat_group(Group_chat groupChat);
}
