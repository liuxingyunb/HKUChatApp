package com.example.chatapp;

import com.example.chatapp.controller.ChatGPTController;
import com.example.chatapp.controller.UserController;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.ChatGPTService;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utils.MultiFile;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatAppApplicationTests {

    @Autowired(required = false)
    UserDao userDao;
    @Autowired
    UserService userService;
    @Autowired
    Personal_chatService personal_chatService;
    @Autowired
    UserController userController;
    @Autowired
    ChatGPTController chatGPTController;
    @Test
    void contextLoads() {
        Response result = userController.getFriendChatHistoryByPage(2,3,0,10);
        System.out.println(result.toString());
    }
    @Test
    void gptResult() {
        Response result = chatGPTController.getResult("作业好难啊我不会做啊");
        System.out.println(result.toString());
    }
    @Test
    void gptTranslate() {
        Response result = chatGPTController.translate("you are a pig");
        System.out.println(result.toString());
    }


//    @Test
//    void friend(){
//
//        System.out.println(userService.selectFriends(11));
//    }
//    @Test
//    void UserTest() {
//        //User user = new User("test1","12313312");
//
//        //userService.addUser(user);
//        //userService.getUserByUsername("test1");
//        userService.deleteUser(7);
//        User user=userService.getUserByUsername("J");
//        System.out.println(user);
//    }
//    @Test
//    void personChat(){
//        Date date=new Date();
//        Personal_chat personalChat=new Personal_chat(1,2,"text","test123",date);
//        personal_chatService.addPersonal_chat(personalChat);
//    }

}
