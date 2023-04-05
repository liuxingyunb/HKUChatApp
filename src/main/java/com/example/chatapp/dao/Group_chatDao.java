package com.example.chatapp.dao;

import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.Group_chat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface Group_chatDao {
    @Insert("insert into group_chat(sender_id, group_id, message_type, content, send_time) value(#{sender_id}, #{group_id}, #{message_type}, #{content}, #{send_time})")
    public void insertGroup_chat(Group_chat groupChat);
    @Delete("DELETE FROM group_chat WHERE id = #{id}")
    public void deleteGroup_chat(int id);
    @Select("SELECT * FROM group_chat where id = #{id}")
    public Group_chat selectGroup_chatById(int id);
    public void updateGroup_chat(Map<String, Object> map);
}
