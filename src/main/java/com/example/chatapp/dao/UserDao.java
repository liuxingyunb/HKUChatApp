package com.example.chatapp.dao;

import com.example.chatapp.model.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into users(username, password, gender, age, hometown, major, mbti, avatar_url, signature, telephone_number, birthday, mail, school, create_time, last_modified_time) values(#{username}, #{password}, #{gender}, #{age}, #{hometown},#{major},#{mbti},#{avatar_url},#{signature}, #{telephone_number}, #{birthday}, #{mail}, #{school},#{create_time}, #{last_modified_time} )")
    public void insertUser(User user);
    @Delete("DELETE FROM users WHERE id = #{user_id}")
    void deleteUserById(int user_id);
    @Select("SELECT * FROM users WHERE id = #{user_id}")
    User selectUserById(int user_id);
    public void updateUser(Map<String, Object> map);


}
