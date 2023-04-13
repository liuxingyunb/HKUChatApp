package com.example.chatapp.service.impl;

import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.ChatService;
import com.example.chatapp.service.Chat_groupService;
import com.example.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<User> recommendPersonal(int userId,int num) {//推荐几个人,可保证至少每3次的匹配不会重复
        Random random = new Random();List<User> ans = new ArrayList<>();
        int maxCounts = userToCountsMap.getOrDefault(userId,0);
        userToCountsMap.put(userId,maxCounts+1);
        List<User> list = userService.selectAllUsers();
        if(list.size()<=num)return list;
         Set<Integer> matchedUsers = map.get(userId);
         if(matchedUsers != null) {
             if(maxCounts>3)matchedUsers.clear();//可调整
             Set<Integer> ansIndex = new HashSet<>();
             for(User user: userService.selectFriends(userId)){
                 matchedUsers.add(user.getId());
             }
             while(ansIndex.size()<num) {
                 int i = random.nextInt(num);
                 while(matchedUsers!=null&&matchedUsers.contains(list.get(i).getId())){i = random.nextInt(num);};
                 ansIndex.add(i);
             }
             for(int i=0;i<list.size();i++) {
                if(ansIndex.contains(list.get(i).getId())) {
                    ans.add(list.get(i));
                    map.get(userId).add(list.get(i).getId());
                    userService.addUserToUser(userId,list.get(i).getUsername());
                }
             }
         } else {
             map.put(userId,new HashSet<>());
             Set<Integer> ansIndex = new HashSet<>();
             while(ansIndex.size()<num) {
                 int i = random.nextInt(num);
                 ansIndex.add(i);
             }
             for(int i=0;i<list.size();i++) {
                 if(ansIndex.contains(list.get(i).getId())) {
                     ans.add(list.get(i));
                     map.get(userId).add(list.get(i).getId());
                     userService.addUserToUser(userId,list.get(i).getUsername());
                 }
             }
         }
         return ans;
    }

    @Override
    public List<Chat_group> recommendGroup(int userId,int num) {//群聊匹配
        return null;
    }


}
