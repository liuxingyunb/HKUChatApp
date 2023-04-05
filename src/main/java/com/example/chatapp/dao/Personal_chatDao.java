package com.example.chatapp.dao;

import com.example.chatapp.model.po.Personal_chat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface Personal_chatDao {
    @Insert("insert into personal_chat(sender_id, receiver_id, message_type, content, send_time) value(#{sender_id}, #{receiver_id}, #{message_type}, #{content}, #{send_time})")
    public void addPersonal_chat(Personal_chat personalChat);
    @Delete("DELETE FROM personal_chat WHERE id = #{id}")
    void deletePersonal_chatById(int chat_id);
    @Select("SELECT * FROM personal_chat WHERE id = #{id}")
    Personal_chat selectPersonal_chatsById(int id);
    public void updatePersonal_chat(Map<String, Object> map);

}
