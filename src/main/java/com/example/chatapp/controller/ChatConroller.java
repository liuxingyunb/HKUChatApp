package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.ChatService;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan("com.example.chatapp.*")
@RestController
@RequestMapping("/recommend")
public class ChatConroller {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/test")
    public Response pin(@RequestBody User user) {
        List<User> users = chatService.recommendPersonal(user.getId(),3);
        return Response.ok("fuck",users);
    }
    @PostMapping("/add")
    public Response pin2(@RequestBody User user) {
        List<User> users = chatService.recommendPersonal(user.getId(),1);
        System.out.println("okkkk");
        return Response.ok("fuck",users);
    }
}
