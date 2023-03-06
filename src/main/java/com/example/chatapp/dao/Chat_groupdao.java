package com.example.chatapp.dao;

import com.example.chatapp.model.po.Chat_group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Chat_groupdao {
    @Insert("insert into chat_groups(id, name, owner_id, create_time, last_active_time, members) value(#{id}, #{name}, #{owner_id}, #{create_time}, #{last_active_time}, #{members})")
    public void addchat_group(Chat_group chatGroup);

}
