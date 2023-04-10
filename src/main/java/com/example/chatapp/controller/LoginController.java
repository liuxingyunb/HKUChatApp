package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatapp.model.vo.Response;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
   private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user){
        if(userService.getUserByUsername(user.getUsername())==null) {
            userService.addUser(user);
            return Response.ok("Register successfully!", user);
        }
        else
            return Response.error("User name has been registered!");
    }

    @PostMapping("/go")
    public Response go(@RequestBody User user){
        if(userService.getUserByUsername(user.getUsername())==null) {
            return Response.error("No such user!");
        }
        else if(userService.getUserByUsername(user.getUsername()).getPassword().equals(user.getPassword()))
            return Response.ok("Login successfully!", userService.getUserByUsername(user.getUsername()));
        else
            return Response.error("Wrong password");
    }
}
