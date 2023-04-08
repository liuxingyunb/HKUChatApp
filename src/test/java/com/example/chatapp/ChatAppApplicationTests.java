package com.example.chatapp;

import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatAppApplicationTests {

    @Autowired(required = false)
    UserDao userDao;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void UserTest() {
        //User user = new User("test1","12313312");

        //userService.addUser(user);
        //userService.getUserByUsername("test1");
        userService.deleteUser(7);
        User user=userService.getUserByUsername("J");
        System.out.println(user);
    }

}
