package com.example.chatapp.controller;

import cn.hutool.core.lang.hash.Hash;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.model.vo.Score;
import com.example.chatapp.utils.ChatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//前端每隔十分钟向后端请求最新倒计时增量，后端每隔十分钟向前端发送两个userid的倒计时增量，每次前端请求完就将相应的倒计时增量值重置避免重复相加。
//@ComponentScan("com.example.chatapp.*")
@Api(tags = "score")
@RestController
@RequestMapping("/score")
public class TimeController {

    @ApiOperation(value = "get the score between two users")
    @PostMapping("/get")
    public Response timeget(@RequestParam String userId1,@RequestParam String userId2) {//return minutes
        HashSet<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(userId1));set.add(Integer.parseInt(userId2));
        Integer time = ChatUtil.sizeMap.get(set);
        if(time == null)return Response.error("No chat content between the users.");
        return Response.ok(userId1+" "+userId2,String.valueOf(time));
    }

    @ApiOperation(value = "get the scores of a user")
    @PostMapping("/getlist")
    public Response timesget(@RequestParam String id) {//return minutes
        Set<HashSet<Integer>> set = ChatUtil.sizeMap.keySet();
        List<Score> list = new LinkedList<>();
        for(HashSet<Integer> cur:set) {
            if(cur.contains(Integer.parseInt(id))) {
                Iterator<Integer> it = cur.iterator();
                int userid1 = it.next();int userid2 = it.next();
                Score score = new Score();
                if(userid1 != Integer.parseInt(id)) score.userId = userid1;
                if(userid2 != Integer.parseInt(id)) score.userId = userid2;
                score.score = ChatUtil.sizeMap.get(cur);
                list.add(score);
            }
        }
        if( list == null)return Response.error("No chat content between the users.");
        return Response.ok("score",list);
    }

    @ApiOperation(value = "get the threshold")
    @PostMapping("/limit")
    public Response thresholdget() {//return minutes
        return Response.ok(String.valueOf(ChatUtil.timeThreshold));
    }


}
