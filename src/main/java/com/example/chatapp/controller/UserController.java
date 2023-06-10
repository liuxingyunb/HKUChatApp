package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.RegisterRequest;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.EmailService;
import com.example.chatapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private EmailService emailService;

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

    @ApiOperation(value = "find password")
    @PostMapping("/find-password")
    public Response findPassword(@RequestBody String username){
        if(userService.getUserByUsername(username)==null)
            return Response.error("No such user!");

        emailService.findPassword(username);
        return Response.ok("Password has been sent to your Email!");
    }

    @ApiOperation(value = "Delete friend")
    @PostMapping("/delete-friend")
    public Response deleteFriend(@RequestParam("name") String deleteuser, @RequestParam("id") int userId){
        userService.removeUserFromUser(userId,deleteuser);

        return Response.ok("Delete Successfully!",userService.selectFriends(userId));
    }
    @ApiOperation(value = "return friend list")
    @PostMapping("/getFriendList")
    public Response getFriendList(@RequestParam("id") int userId){
        return Response.ok("friend list",userService.selectFriends(userId));
    }

}
