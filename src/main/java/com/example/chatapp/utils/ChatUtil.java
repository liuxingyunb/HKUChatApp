package com.example.chatapp.utils;

import com.example.chatapp.model.po.User;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.service.UserService;
import com.example.chatapp.websocket.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.timer.TimerMBean;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ChatUtil {


    static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        ChatUtil.userService = userService;
    }
    public  static ConcurrentHashMap<HashSet<Integer>,Integer> minuteMap = new ConcurrentHashMap<>();//聊天双方及时间
    public  static ConcurrentHashMap<HashSet<Integer>,Integer> sizeMap = new ConcurrentHashMap<>();//聊天双方及长度（单位）,每个长度1分钟

    public  static void startChatStatisticsTask() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(minuteMap != null) {//
                    for(HashSet<Integer> set:sizeMap.keySet()) {
                        Iterator<Integer> it = set.iterator();
                        Integer userId1 = it.next();Integer userId2 = it.next();
                        List<Integer> list = userService.selectFriends(userId1).stream().map(User::getId).collect(Collectors.toList());
                        if(!list.contains(userId2)) {
                            if(minuteMap.containsKey(set))minuteMap.remove(set);
                            if(sizeMap.containsKey(set))sizeMap.remove(set);
                            continue;}
                        int size = sizeMap.get(set);
                        minuteMap.put(set,size);
                        sizeMap.put(set,0);//重置
                    }
                }
            }
        };
        long delay = 0;  // 延迟0毫秒，表示立即执行第一次任务
        long period = 10 * 60 * 1000;  // 十分钟的毫秒数
        timer.schedule(task, delay, period);
    }
}
