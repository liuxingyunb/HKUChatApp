package com.example.chatapp.controller;


import com.example.chatapp.dao.Chat_groupDao;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.*;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.*;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试 控制器
 */
@ComponentScan("com.example.chatapp.*")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    @Lazy
    private UserService userService;
    @Autowired
    @Lazy
    private Chat_groupService chat_groupService;
    @Autowired
    @Lazy
    private Group_chatService group_chatService;
    @Autowired
    @Lazy
    private Personal_chatService personal_chatService;
    @Autowired
    @Lazy
    private Photo_wallService photo_wallService;
    @Autowired
    private ChatGPTService chatGPTService;
    @Autowired
    private ChatService chatService;
    @PostMapping("/pin")
    public Response pin(@RequestBody User user) {
        userService.addUser(user);
        return Response.ok("fuck",user);
    }
    @PostMapping("/pin2")
    public Response pin2(@RequestBody Chat_group chat_group) {
        System.out.println(chat_group);
        chat_groupService.addChat_group(chat_group);
//        chat_groupService.deleteChat_group(2);
        Chat_group g = chat_groupService.getChat_groupById(4);
        System.out.println(g);
        Map<String,Object> map = new HashMap<>();
        map.put("id",4);
        map.put("name","Leo123");
        chat_groupService.updateChat_group(map);
        return Response.ok("fuck",chat_group);
    }
    @PostMapping("/pin3")
    public Response pin3(@RequestBody Group_chat group_chat) {
        group_chat.setSend_time(new Date());
        group_chatService.addGroup_chat(group_chat);
//        group_chatService.deleteGroup_chat(2);
        Group_chat g = group_chatService.getGroup_chatById(5);
        System.out.println(g);
        Map<String,Object> map = new HashMap<>();
        map.put("id",5);
        map.put("content","爸爸巴巴");
        group_chatService.updateGroup_chat(map);
        return Response.ok("fuck",group_chat);
    }
    @PostMapping("/pin4")
    public Response pin4(@RequestBody Personal_chat personal_chat) {
        personal_chat.setSend_time(new Date());
        personal_chatService.addPersonal_chat(personal_chat);
//        personal_chatService.deletepersonal_chat(2);
        Personal_chat g = personal_chatService.getPersonal_chatById(2);
        System.out.println(g);
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("content","尔耳儿儿");
        personal_chatService.updatePersonal_chat(map);
        return Response.ok("fuck",personal_chat);
    }
    @PostMapping("/pin5")
    public Response pin5(@RequestBody Photo_wall photo_wall) {
        photo_wallService.addPhoto_wall(photo_wall);
//        photo_wallServiceService.deletephoto_wallService(2);
        Photo_wall g = photo_wallService.getPhoto_wallById(3);
        System.out.println(g);
        Map<String,Object> map = new HashMap<>();
        map.put("id",3);
        map.put("photo_url","D:\\Leo123");
        photo_wallService.updatePhoto_wall(map);
        return Response.ok("fuck",photo_wall);
    }
    @PostMapping("/pin6")
    public Response pin6(String name) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        User u = userService.getUser(map).get(0);
        return Response.ok("fuck",u);
    }
    @PostMapping("/pin7")
    public Response pin7(@RequestParam int id) {
        List<User> l = chat_groupService.selectMembers(id);
        return Response.ok("fuck",l);
    }
    @PostMapping("/pin8")
    public Response pin8(@RequestParam String username) {
        List<Chat_group> l = userService.selectChatGroupsContainUser(username);
        return Response.ok("fuck",l);
    }
    @PostMapping("/pin9")
    public Response pin9(@RequestParam int id, @RequestParam String username) {
//        chat_groupService.addMemberToChatGroup(id,username);
        userService.addFriendIdToUser(id,username);
//        userService.removeUserFromUser(id,username);
        User user = userService.getUserById(id);
        return Response.ok("fuck",user);
    }
    @PostMapping("/pin10")
    public Response pin10() {
        //List<Chat_group> l = chat_groupService.selectAllGroups();
        List<User> l=userService.selectFriends(2);
        return Response.ok("fuck",l);
    }
    @PostMapping("/pin11")
    public Response pin11() {
        List<User> l = userService.selectFriends(2);
        return Response.ok("fuck",l);
    }
    @GetMapping("/db")
    public Response db() {
        return Response.ok();
    }
    @GetMapping("/history")
    public Response history(@RequestParam int senderId, @RequestParam int receiverId) {
        List<Personal_chat> history = personal_chatService.showHistory(senderId,receiverId);
        return Response.ok("history",history);
    }
    @PostMapping("/add")
    public Response add(@RequestParam int id, @RequestParam String friendName) {
        userService.addFriendIdToUser(id,friendName);
        return Response.ok("history",userService.getUserById(id).getMembers());
    }
    @PostMapping("/remove")
    public Response remove(@RequestParam int id, @RequestParam String friendName) {
        userService.removeUserIdFromUser(id,friendName);
        return Response.ok("history",userService.getUserById(id).getMembers());
    }
    @PostMapping("/addGroup")
    public Response addGroup(@RequestParam int id, @RequestParam String name) {
        chat_groupService.addMemberToChatGroup(id,name);
        return Response.ok("history",chat_groupService.getChat_groupById(id));
    }
    @PostMapping("/removeGroup")
    public Response removeGroup(@RequestParam int id, @RequestParam String name) {
       chat_groupService.removeMemberIdFromGroup(id,name);
        return Response.ok("history",chat_groupService.getChat_groupById(id));
    }
    @PostMapping("/getResult")
    public Response getResult(@RequestParam String question) {
        return Response.ok("answer:", chatGPTService.computeQuestion(question));
    }
    @PostMapping("/addPerson")
    public Response addPerson(@RequestParam int userId) {
        List<User> users = chatService.recommendPersonal(userId,3);
        return Response.ok("ok",users);
    }
}
