package com.example.chatapp.dao;

import com.example.chatapp.model.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface Userdao {

    @Insert("insert into user(uuid, username, password, phone, mail, age, deleted, sex,school,imgurl,birthday) values(#{uuid}, #{username}, #{password}, #{phone}, #{mail}, #{age}, #{deleted},#{sex},#{school},#{imgurl},#{birthday})")
    public int insertUser(User user);

}
