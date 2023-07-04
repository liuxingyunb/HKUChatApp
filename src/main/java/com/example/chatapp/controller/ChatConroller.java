package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.ChatService;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ComponentScan("com.example.chatapp.*")
@RestController
@Api(tags = "Recommendation")
@RequestMapping("/recommend")
public class ChatConroller {
    @Autowired
    @Lazy
    private ChatService chatService;
    @Autowired
    @Lazy
    private UserService userService;
    @ApiOperation(value = "recommend persons to user")
    @PostMapping("/add")
    public Response recommendPerson(@RequestParam int id) {
        List<User> users = chatService.recommendPersonal(id,3);
        return Response.ok("ok",users);
    }
}
