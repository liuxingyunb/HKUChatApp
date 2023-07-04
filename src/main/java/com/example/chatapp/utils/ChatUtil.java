package com.example.chatapp.utils;

import com.alibaba.fastjson2.JSON;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Message;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.service.UserService;
import com.example.chatapp.websocket.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.management.timer.TimerMBean;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Component
public class ChatUtil {


    public static int timeThreshold = 10000;

    public static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    static UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        ChatUtil.userService = userService;
    }
    public  static ConcurrentHashMap<HashSet<Integer>,Integer> sizeMap = new ConcurrentHashMap<>();//聊天双方及长度（单位）,每个长度1分钟

    public  static void startChatStatisticsTask() throws Exception{
        FileWriter writer = new FileWriter("score.txt");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(sizeMap.size());
                Message message = new Message();
                message.setType("system");
                message.setContent("update the types of friends");
                if(sizeMap != null) {//
                    for(HashSet<Integer> set:sizeMap.keySet()) {
                        Iterator<Integer> it = set.iterator();
                        Integer userId1 = it.next();Integer userId2 = it.next();
                        List<Integer> list = userService.selectFriends(userId1).stream().map(User::getId).collect(Collectors.toList());
                        if(!list.contains(userId2)) {
                            if(sizeMap.containsKey(set))sizeMap.remove(set);continue;}
                        sizeMap.put(set,sizeMap.get(set)-100);it = set.iterator();
                        System.out.println(it.next()+" "+it.next()+" "+sizeMap.get(set));
                        it = set.iterator();
                        Server server1 = Server.WEBSOCKET_MAP.get(it.next());
                        Server server2 = Server.WEBSOCKET_MAP.get(it.next());
                        server1.session.getAsyncRemote().sendText(JSON.toJSONString(message));
                        server2.session.getAsyncRemote().sendText(JSON.toJSONString(message));

                    }
                    try{
                    String tmp = JSON.toJSONString(sizeMap);
                    writer.write(tmp);writer.flush();writer.close();}
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        long delay = 0;  // 延迟0毫秒，表示立即执行第一次任务
        long period = 24*60;  // 1 day的分钟数
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                task.run();
            }
        },delay, period, TimeUnit.MINUTES);
    }
}
