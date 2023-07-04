package com.example.chatapp.dao;

import com.example.chatapp.model.po.Personal_chat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface Personal_chatDao {
    @Insert("insert into personal_chat(id, sender_id, receiver_id, message_type, content, send_time) value(#{id}, #{sender_id}, #{receiver_id}, #{message_type}, #{content}, #{send_time})")
    public void addPersonal_chat(Personal_chat personalChat);
    @Delete("DELETE FROM personal_chat WHERE id = #{id}")
    void deletePersonal_chatById(int chat_id);
    @Select("SELECT * FROM personal_chat WHERE id = #{id}")
    Personal_chat selectPersonal_chatsById(int id);
    public void updatePersonal_chat(Map<String, Object> map);
    List<Personal_chat> getChatMessages(@Param("senderId") int senderId, @Param("receiverId") int receiverId);
    List<Personal_chat> getChatMessagesByPage(@Param("senderId") int senderId, @Param("receiverId") int receiverId, @Param("offset") int offset, @Param("pageSize") int pageSize);

}
