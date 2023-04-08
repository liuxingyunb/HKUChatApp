package com.example.chatapp.dao;

import com.example.chatapp.model.po.Chat_group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Repository
@Mapper
public interface Chat_groupDao {
    @Insert("insert into chat_groups(name, owner_id, create_time, last_active_time, members) value(#{name}, #{owner_id}, #{create_time}, #{last_active_time}, #{members})")
    public void addChat_group(Chat_group chatGroup);
    @Delete("DELETE FROM chat_groups WHERE id = #{id}")
    public void deleteChat_group(int id);
    @Select("SELECT * FROM chat_groups where id = #{id}")
    public Chat_group selectChat_groupById(int id);
    public void updateChat_group(Map<String, Object> map);

}
