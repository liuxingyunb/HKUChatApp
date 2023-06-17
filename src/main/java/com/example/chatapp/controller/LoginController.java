package com.example.chatapp.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.RegisterRequest;
import com.example.chatapp.service.EmailService;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utilize.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.chatapp.model.vo.Response;

import java.util.*;

@RestController
@Api(tags = "Login and registger")
@RequestMapping("/login")
@RequiresGuest
public class LoginController {

    @Autowired
   private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Personal_chatService personal_chatService;

    private Map<String, String> verificationCodeMap = new HashMap<>();

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public Response register(@RequestBody RegisterRequest registerRequest){
        User user=registerRequest.getUser();
        String code=registerRequest.getCode();
        if(code=="")
            return Response.error("Need Email verification!");
        if(userService.getUserByUsername(user.getUsername())==null) {
            //if (code != null && code.equals(verificationCodeMap.get(user.getUsername()))) {
                // 验证码匹配成功且未过期，更新用户的邮箱验证状态为已验证
                userService.addUser(user);
                return Response.ok("Register successfully!", user);
           // } else {
           //     return Response.error("Invalid verification code!");
           // }

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
            // 登录成功，生成JWT令牌
            String jwtToken = JwtUtils.generateToken(user.getId(),user.getUsername());

            User myuser=userService.getUserByUsername(user.getUsername());
            int[] ids=userService.selectFriendId(myuser.getId());

            JSONObject data = new JSONObject();
            data.put("user", myuser);
            data.put("token", jwtToken);

            return Response.ok("Login successfully!", data);
        }
        else
            return Response.error("Wrong password");
    }

    @ApiOperation(value = "Send verification code")
    @PostMapping("/send-verifyemail")
    public Response sendEmail(@RequestBody User user) {
        String username = user.getUsername();
        String email = user.getMail();
        if(emailService.isValidEmail(email)==false)
            return Response.error("Invalid email address!");
        String code = emailService.generateVerificationCode();
        emailService.sendVerificationEmail(email,code);
        emailService.saveVerificationCode(username,code);
        verificationCodeMap=emailService.getVerificationCodeMap();

        return Response.ok("Send verification code to your Email!");
    }

    @ApiOperation(value = "find password")
    @PostMapping("/find-password")
    public Response findPassword(@RequestParam("username") String username, @RequestParam("email") String email){
        if(userService.getUserByUsername(username)==null)
            return Response.error("No such user!");
        if(!userService.getUserByUsername(username).getMail().equals(email))
            return Response.error("Wrong Email Address!");

        emailService.findPassword(username);
        return Response.ok("Password has been sent to your Email!");
    }
}
