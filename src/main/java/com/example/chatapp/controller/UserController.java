package com.example.chatapp.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.RegisterRequest;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.EmailService;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;


@RestController
@Api(tags = "User information management")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Personal_chatService personal_chatService;


    @ApiOperation(value = "update")
    @PostMapping("/update")
    public Response update(@RequestBody User user) {

        Map<String, Object> propertyValueMap = new HashMap<>();

        Class<?> clazz = user.getClass();
        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {

                field.setAccessible(true);

                String propertyName = field.getName();
                Object propertyValue = field.get(user);

                propertyValueMap.put(propertyName, propertyValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        userService.updateUser(propertyValueMap);
        return Response.ok("Update Successfully");
    }

    @ApiOperation(value = "Delete friend")
    @PostMapping("/delete-friend")
    public Response deleteFriend(@RequestParam("name") String deleteuser, @RequestParam("id") int userId){
        userService.removeUserIdFromUser(userId,deleteuser);

        return Response.ok("Delete Successfully!",userService.selectFriends(userId));
    }


    @ApiOperation(value = "return friend list")
    @PostMapping("/getFriendList")
    public Response getFriendList(@RequestParam("id") int userId){
        return Response.ok("friend list",userService.selectFriends(userId));
    }
    @ApiOperation(value = "return friend list")
    @PostMapping("/getFriendListByPage")
    public Response getFriendListByPage(@RequestParam("id") int userId, @RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize){
        return Response.ok("friend list",userService.selectFriendsByPage(userId,offset,pageSize));
    }
    @ApiOperation(value = "return user info")
    @PostMapping("/getUserInfo")
    public Response getUserInfo(@RequestParam("id") int userId){
        return Response.ok("user info",userService.getUserById(userId));
    }

    @ApiOperation(value = "Update chat information")
    @PostMapping("/getChatInfo")
    public Response getChatInfo(@RequestParam("id") int userId){
        User user=userService.getUserById(userId);

        int[] ids=userService.selectFriendId(user.getId());

        if(ids==null||ids.length==0)
            return Response.ok("Chat Information", null);

        JSONArray data = new JSONArray();
        for(int i=0;i<ids.length;i++){
            JSONObject userInfo = new JSONObject();
            userInfo.put("otherUserName",userService.getUserById(ids[i]).getUsername());
            userInfo.put("otherUserId",userService.getUserById(ids[i]).getId());
            userInfo.put("otherAvatarUrl", userService.getUserById(ids[i]).getAvatar_url());
            userInfo.put("messages",personal_chatService.showHistory(user.getId(),ids[i]));

            data.add(userInfo);
        }

        return Response.ok("Chat Information", data);
    }
    @ApiOperation(value = "Update chat information")
    @PostMapping("/getChatInfoByPage")
    public Response getChatInfoByPage(@RequestParam("id") int userId,@RequestParam("offsize") int offsize, @RequestParam("pageSize") int pageSize){
        User user=userService.getUserById(userId);

        int[] ids=userService.selectFriendId(user.getId());

        if(ids==null||ids.length==0)
            return Response.ok("Chat Information", null);

        JSONArray data = new JSONArray();
        for(int i=0;i<ids.length;i++){
            JSONObject userInfo = new JSONObject();
            userInfo.put("otherUserName",userService.getUserById(ids[i]).getUsername());
            userInfo.put("otherUserId",userService.getUserById(ids[i]).getId());
            userInfo.put("otherAvatarUrl", userService.getUserById(ids[i]).getAvatar_url());
            userInfo.put("messages",personal_chatService.showHistoryByPage(user.getId(),ids[i],offsize,pageSize));
            data.add(userInfo);
        }

        return Response.ok("Chat Information", data);
    }





}
