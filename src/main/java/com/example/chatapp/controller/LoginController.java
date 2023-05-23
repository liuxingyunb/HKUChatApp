package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatapp.model.vo.Response;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@Api(tags = "Login and registger")
@RequestMapping("/login")
public class LoginController {

    @Autowired
   private UserService userService;

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public Response register(@RequestBody User user){
        if(userService.getUserByUsername(user.getUsername())==null) {
            userService.addUser(user);
            return Response.ok("Register successfully!", user);
        }
        else
            return Response.error("User name has been registered!");
    }

    @ApiOperation(value = "login")
    @PostMapping("/go")
    public Response go(@RequestBody User user){
        if(userService.getUserByUsername(user.getUsername())==null) {
            return Response.error("No such user!");
        }
        else if(userService.getUserByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
            List<User> list=new LinkedList<>();
            list.add(userService.getUserByUsername(user.getUsername()));
            if(userService.selectFriends(userService.getUserByUsername(user.getUsername()).getId())!=null)
                list.addAll(userService.selectFriends(userService.getUserByUsername(user.getUsername()).getId()));
            return Response.ok("Login successfully!", list);
        }
        else
            return Response.error("Wrong password");
    }
}
