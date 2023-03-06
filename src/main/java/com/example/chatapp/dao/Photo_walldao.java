package com.example.chatapp.dao;

import com.example.chatapp.model.po.Photo_wall;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Photo_walldao {

    @Insert("insert into photo_wall(id, user_id, photo_url,create_time) value(#{id}, #{user_id}, #{photo_url},#{create_time})" )
    public void addphoto(Photo_wall photoWall);
}
