package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.utils.ChatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

//前端每隔十分钟向后端请求最新倒计时增量，后端每隔十分钟向前端发送两个userid的倒计时增量，每次前端请求完就将相应的倒计时增量值重置避免重复相加。
//@ComponentScan("com.example.chatapp.*")
@Api(tags = "Count down")
@RestController
@RequestMapping("/countdown")
public class TimeController {
    @ApiOperation(value = "get the countdown minutes between two users")
    @PostMapping("/get")
    public Response timeget(@RequestParam String userId1,@RequestParam String userId2) {//return minutes
        HashSet<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(userId1));set.add(Integer.parseInt(userId2));
        Integer time = ChatUtil.sizeMap.get(set);
        System.out.println("countdown"+time);
        if(time == null)return Response.error("No chat content between the users.");
        return Response.ok(userId1+" "+userId2,String.valueOf(time));
    }
}
