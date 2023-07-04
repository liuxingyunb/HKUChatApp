package com.example.chatapp.service.impl;

import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.ChatService;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    UserService userService;

    @Autowired
    Chat_groupService chat_groupService;


    static HashMap<Integer,Integer> userToCountsMap = new HashMap<>();//user匹配次数

    static HashMap<Integer, Set<Integer>> map = new HashMap<>();//userid,matched users
    @Override
    public List<User> contactPersonal(int userId) {
        try {
        return userService.selectFriends(userId);}
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Chat_group> contactGroup(int userId) {
        try {
            return userService.selectChatGroupsContainUser(userService.getUserById(userId).getUsername());}
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> recommendPersonal(int userId,int num) {//推荐几个人,可保证至少每3次的匹配不会重复,如果好友足够多了就拒绝推荐
        Random random = new Random();List<User> ans = new ArrayList<>();
        int maxCounts = userToCountsMap.getOrDefault(userId,0);
        userToCountsMap.put(userId,maxCounts+1);
        List<User> list = userService.selectAllUsers();
        if(list.size()<=num)return list;
         Set<Integer> matchedUsers = map.get(userId);
         if(matchedUsers==null){map.put(userId,new HashSet<>());matchedUsers = map.get(userId);}
        if(maxCounts>3)matchedUsers.clear();//可调整
        if(userService.selectFriends(userId)!=null){
         List<User> tmplist = userService.selectFriends(userId);
         for(User user: tmplist){
            if(user != null){matchedUsers.add(user.getId());System.out.println(user.getId());}
        }}
         if(matchedUsers != null) {
             Set<Integer> ansIndex = new HashSet<>();
             matchedUsers.add(userId);
             if(list.size()-matchedUsers.size() <num)return null;//可调整
             while(ansIndex.size()<num) {
                 int i = random.nextInt(list.size());
                 while(matchedUsers!=null&&matchedUsers.contains(list.get(i).getId())){i = random.nextInt(list.size());};
                 ansIndex.add(i);
             }
             for(int i=0;i<list.size();i++) {
                if(ansIndex.contains(i)) {
                    ans.add(list.get(i));
                    map.get(userId).add(list.get(i).getId());
                    System.out.println(list.get(i).getId());
                }
             }
         } else {
             map.put(userId,new HashSet<>());
             Set<Integer> ansIndex = new HashSet<>();
             while(ansIndex.size()<num) {
                 int i = random.nextInt(list.size());
                 while(list.get(i).getId()==userId||ansIndex.contains(i)||matchedUsers.contains(list.get(i).getId()))i = random.nextInt(list.size());
                 ansIndex.add(i);
             }
             for(int i=0;i<list.size();i++) {
                 if(ansIndex.contains(i)&&userId!=list.get(i).getId()) {
                     ans.add(list.get(i));
                     map.get(userId).add(list.get(i).getId());
                 }
             }
             map.get(userId).add(userId);

         }
         System.out.println(ans);
         for(User user:ans) {
             userService.addFriendIdToUser(userId,user.getUsername());
             userService.addFriendIdToUser(user.getId(),userService.getUserById(userId).getUsername());
         }
         return ans;
    }

    @Override
    public List<Chat_group> recommendGroup(int userId,int num) {//群聊匹配
        return null;
    }


}
