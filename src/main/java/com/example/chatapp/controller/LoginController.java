package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.RegisterRequest;
import com.example.chatapp.service.EmailService;
import com.example.chatapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatapp.model.vo.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Login and registger")
@RequestMapping("/login")
public class LoginController {

    @Autowired
   private UserService userService;

    @Autowired
    private EmailService emailService;

    private Map<String, String> verificationCodeMap = new HashMap<>();

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public Response register(@RequestBody RegisterRequest registerRequest){
        User user=registerRequest.getUser();
        String code=registerRequest.getCode();
        if(code=="")
            return Response.error("Need Email verification!");
        if(userService.getUserByUsername(user.getUsername())==null) {
            if (code != null && code.equals(verificationCodeMap.get(user.getUsername()))) {
                // 验证码匹配成功且未过期，更新用户的邮箱验证状态为已验证
                userService.addUser(user);
                return Response.ok("Register successfully!", user);
            } else {
                return Response.error("Invalid verification code!");
            }
           // return Response.ok("Register successfully!", user);
        }
        else
            return Response.error("User name has been registered!");
    }
/*
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
    }*/

  @ApiOperation(value = "login")
  @PostMapping("/go")
  public Response go(@RequestBody User requestUser){
      String username = requestUser.getUsername();
      String password = requestUser.getPassword();

      try {
          // 创建UsernamePasswordToken对象，用于传递用户名和密码
          UsernamePasswordToken token = new UsernamePasswordToken(username, password);

          // 调用Subject的login方法进行登录
          Subject subject = SecurityUtils.getSubject();
          subject.login(token);

          // 登录成功，可以根据需要执行其他操作，例如返回用户信息等
          String token_username = token.getUsername();
          User user = userService.getUserByUsername(token_username);

          List<User> list = new ArrayList<>();
          list.add(user);
          if(userService.selectFriends(user.getId()) != null)
              list.addAll(userService.selectFriends(user.getId()));

          return Response.ok("Login successfully!", list);
      } catch (UnknownAccountException e) {
          return Response.error("No such user!");
      } catch (IncorrectCredentialsException e) {
          return Response.error("Wrong password");
      } catch (AuthenticationException e) {
          return Response.error("Authentication failed");
      }
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
}
