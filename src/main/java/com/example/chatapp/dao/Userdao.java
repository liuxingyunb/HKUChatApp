package com.example.chatapp.dao;

import com.example.chatapp.model.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface Userdao {

    @Insert("insert into users(id, username, password, gender, age, hometown, major, mbti, avatar_url, signature, telephone_number, birthday, mail, school, create_time, last_modified_time) values(#{id}, #{username}, #{password}, #{gender}, #{age}, #{hometown},#{major},#{mbti},#{avatar_url},#{signature}, #{telephone_number}, #{birthday}, #{mail}, #{school},#{create_time}, #{last_modified_time} )")
    public void insertUser(User user);

}
