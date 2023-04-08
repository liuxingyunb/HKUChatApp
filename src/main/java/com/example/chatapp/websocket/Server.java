package com.example.chatapp.websocket;

import com.alibaba.fastjson2.JSON;
import com.example.chatapp.model.po.Group_chat;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.vo.Message;
import com.example.chatapp.service.Group_chatService;
import com.example.chatapp.service.Personal_chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

@ServerEndpoint("/chat/{id}")
@Component
public class Server {

    @Autowired
    Personal_chatService personal_chatService;

    @Autowired
    Group_chatService group_chatService;

    public static ConcurrentHashMap<String, Server> WEBSOCKET_MAP = new ConcurrentHashMap<>();//存放所有用户

    private Session session;//当前session

    private String userId;//当前用户

    @OnOpen
    public void OnOpen(Session session, @PathParam("id") String id) throws Exception{

        this.userId = id;
        this.session = session;
        Server server = WEBSOCKET_MAP.get(id);
        if (server == null) {
            WEBSOCKET_MAP.put(id,server);
        } else {
            session.close();
        }
    }

    @OnMessage
    public void onMessage(String tmp) {
        //发送多媒体文件，先发text即附属信息再发二进制流文件sendbinary，分块发送
        //多媒体超大型文件存储在本地，路径地址存储在content里
        Message cur = JSON.parseObject(tmp, Message.class);
        Integer fromUserId = cur.getFromId();
        Integer toUserId = cur.getToId();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(cur.getType() == null || cur.getType().equals("text") ) {
            Date date = new Date();//smartphone 核心逻辑
            if(cur.getIsGroup()==1) {
                Group_chat tmpGroupChat = new Group_chat(cur.getFromId(),cur.getToId(),"text",cur.getContent(),date);
                group_chatService.addGroup_chat(tmpGroupChat);
                //给群聊发的话就给该群聊的所有人发送包括自己即可
            } else {
                Personal_chat tmpPersonalChat = new Personal_chat(cur.getFromId(),cur.getToId(),"text",cur.getContent(),date);
                personal_chatService.addPersonal_chat(tmpPersonalChat);
                sendText(String.valueOf(fromUserId),cur);
            }
        } else {//system,video/image等情况

        }
    }

    @OnClose
    public void onClose() {
        WEBSOCKET_MAP.remove(this.userId);
    }

    public static Future<Void> sendText(String id,Message message) {//目标id
        Server webSocketServer = WEBSOCKET_MAP.get(id);
        return webSocketServer.session.getAsyncRemote().sendText(JSON.toJSONString(message));
    }

    public static Future<Void> sendMultimedia(String id, Message message,byte[] data) {//目标id
        Server webSocketServer = WEBSOCKET_MAP.get(id);
        return webSocketServer.session.getAsyncRemote().sendText(JSON.toJSONString(message));
        //还应再send data
    }

    private static String robot(String message) {//chatgpt
        return "";
    }

}
