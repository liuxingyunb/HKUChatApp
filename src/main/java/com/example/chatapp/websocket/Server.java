package com.example.chatapp.websocket;


import com.alibaba.fastjson2.JSON;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.Group_chat;
import com.example.chatapp.model.po.Personal_chat;
import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Message;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.Group_chatService;
import com.example.chatapp.service.Personal_chatService;
import com.example.chatapp.utils.ChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@ServerEndpoint("/chat/{id}")//每个连接的客户端都会有自己的一个server实例
@Component
public class Server {//autowired失效


    public static Personal_chatService personal_chatService;

    public static Group_chatService group_chatService;

    public static Chat_groupService chat_groupService;

    public static ConcurrentHashMap<String, Server> WEBSOCKET_MAP = new ConcurrentHashMap<>();//存放所有用户

    private Session session;//当前session

    private String userId;//当前用户

    @Autowired
    public void setPersonal_chatService(Personal_chatService personal_chatService) {
        Server.personal_chatService = personal_chatService;
    }

    @Autowired
    public void setGroup_chatService(Group_chatService Group_chatService) {
        Server.group_chatService = Group_chatService;
    }

    @Autowired
    public void setChat_groupService(Chat_groupService chat_groupService) {
        Server.chat_groupService = chat_groupService;
    }


    @OnOpen
    public void OnOpen(Session session, @PathParam("id") String id) throws Exception{

        this.userId = id;
        this.session = session;
        Server server = WEBSOCKET_MAP.get(id);
//        System.out.println(id +" connecting");
        if (server == null) {
            WEBSOCKET_MAP.put(id,this);
        } else {
//            System.out.println("multiple!!!!!");
            WEBSOCKET_MAP.put(id,this);
//            session.close();
        }

    }

    @OnMessage
    public void onMessage(String tmp) {//缺少聊天记录统计及倒计时激励
        //发送多媒体文件，也发text即文件附属信息（发文件的时候前端起个全局独一无二的名字）,文件存储获取走controller逻辑
        //多媒体超大型文件存储在本地，路径地址存储在content里
        System.out.println(tmp);
        Message cur = JSON.parseObject(tmp, Message.class);
        Integer fromUserId = cur.getFromId();
        Integer toUserId = cur.getToId();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        int timeSize = 0;String content = cur.getContent();
        if(cur.getType().equals("video") || cur.getType().equals("image"))timeSize+=60;
        else for(int i=0;i<content.length();i++) {
            if(isChinese(content.charAt(i)))timeSize+=5;
            else timeSize+=1;
        }
        HashSet<Integer> set = new HashSet<>();set.add(fromUserId);set.add(toUserId);
        if(ChatUtil.sizeMap.containsKey(set)){int sizeTmp = ChatUtil.sizeMap.get(set);ChatUtil.sizeMap.put(set,sizeTmp+timeSize);}
        else {ChatUtil.sizeMap.put(set,timeSize+100);}
        if(cur.getType()=="system") return;
        Date date = new Date();//核心逻辑
            if(cur.getIsGroup()==1) {
                Group_chat tmpGroupChat = new Group_chat(cur.getFromId(),cur.getToId(),cur.getType(),cur.getContent(),date);
                group_chatService.addGroup_chat(tmpGroupChat);
                Chat_group group = chat_groupService.getChat_groupById(cur.getToId());
                if(group == null)return;
                List<User> members = chat_groupService.selectMembers(group.getId());
                boolean flag = true;
                List<Future<Void>> tasks = new ArrayList<>();
                for(User user:members) {
                    Future<Void> tmpF = sendText(String.valueOf(user.getId()),cur);
                    tasks.add(tmpF);
                    flag&=tmpF.isDone();
                }
                while(!flag) {
                    flag = true;
                    for(Future<Void> k :tasks)flag&=k.isDone();
                }
                System.out.println("success");
                //给群聊发的话就给该群聊的所有人发送包括自己即可
            } else {
                Personal_chat tmpPersonalChat = new Personal_chat(cur.getId(),cur.getFromId(),cur.getToId(),cur.getType(),cur.getContent(),date);
                personal_chatService.addPersonal_chat(tmpPersonalChat);
                Future<Void> tmpf = sendText(String.valueOf(toUserId),cur);
                while(!tmpf.isDone());
                Future<Void> tmpr = sendText(String.valueOf(fromUserId),cur);
                while(!tmpr.isDone());
                System.out.println("success");
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

    public static Future<Void> sendMultimedia(String id, Message message) {//目标id
        Server webSocketServer = WEBSOCKET_MAP.get(id);
        return webSocketServer.session.getAsyncRemote().sendText(JSON.toJSONString(message));
    }
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    private static String robot(String message) {//chatgpt
        return "";
    }


}
