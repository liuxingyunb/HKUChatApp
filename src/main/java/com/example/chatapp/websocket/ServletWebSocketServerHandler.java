package com.example.chatapp.websocket;

import com.alibaba.fastjson2.JSON;
import com.example.chatapp.model.po.Group_chat;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.vo.Message;
import com.example.chatapp.service.Group_chatService;
import com.example.chatapp.service.Personal_chatService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

@Component
public class ServletWebSocketServerHandler extends TextWebSocketHandler {

    public static ConcurrentHashMap<String, WebSocketSession> WEBSOCKET_MAP = new ConcurrentHashMap<>();//存放所有用户

    private WebSocketSession session;//当前session

    private String userId;//当前用户

    private boolean isUser = false;

    public static Queue<WebSocketSession> queue = new LinkedList<>();
    public static Personal_chatService personal_chatService;

    public static Group_chatService group_chatService;

    @Autowired
    public void setPersonal_chatService(Personal_chatService personal_chatService) {
        ServletWebSocketServerHandler.personal_chatService = personal_chatService;
    }

    @Autowired
    public void setGroup_chatService(Group_chatService Group_chatService) {
        ServletWebSocketServerHandler.group_chatService = Group_chatService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        //连接建立
        this.session = session;
        queue.add(this.session);
        System.out.println(session.getId());
        System.out.println("success");
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        //接收消息

        String payload = message.getPayload();
        if(payload.startsWith("[[userid]]")){
            WEBSOCKET_MAP.put(payload.substring(10).strip(),queue.poll());
            this.userId = payload.substring(10).strip();
            System.out.println(this.userId);
            return;
        }
        else {
//            Message mes = new Message();
//            mes.setContent("Hello, I'm Eric Gao");
//            mes.setFromId(0);mes.setToId(1);
//            mes.setType("text");mes.setIsGroup(0);
//            session.sendMessage(new TextMessage(JSON.toJSONString(mes)));
            String tmp = payload;
            Message cur = JSON.parseObject(tmp, Message.class);
            Integer fromUserId = cur.getFromId();
            Integer toUserId = cur.getToId();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (cur.getType() == null || cur.getType().equals("text")) {
                Date date = new Date();//smartphone 核心逻辑
                if (cur.getIsGroup() == 1) {
                    Group_chat tmpGroupChat = new Group_chat(cur.getFromId(), cur.getToId(), "text", cur.getContent(), date);
                    group_chatService.addGroup_chat(tmpGroupChat);
                    //给群聊发的话就给该群聊的所有人发送包括自己即可
                } else {
                    Personal_chat tmpPersonalChat = new Personal_chat(cur.getId(),cur.getFromId(), cur.getToId(), "text", cur.getContent(), date);
//                personal_chatService.addPersonal_chat(tmpPersonalChat);
                    System.out.println("success");
                    sendText(String.valueOf(toUserId), cur);
                }
            } else {//system,video/image等情况

            }
        }
    }

    @Override
    public void handleTransportError(@NonNull WebSocketSession session, @NonNull Throwable exception) throws Exception {
        //异常处理
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus closeStatus) throws Exception {
        //连接关闭
    }

    @Override
    public boolean supportsPartialMessages() {
        //是否支持接收不完整的消息
        return false;
    }

    public static void sendText(String id, Message message) throws Exception{//目标id
        WebSocketSession session = WEBSOCKET_MAP.get(id);
        System.out.println("send: "+message.getContent());
        session.sendMessage(new TextMessage(JSON.toJSONString(message)));
    }

}