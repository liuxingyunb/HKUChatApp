package com.example.chatapp.service.impl;

import com.example.chatapp.dao.Chat_groupDao;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.exception.ChatGroupNotFoundException;
import com.example.chatapp.exception.UserAlreadyExistsException;
import com.example.chatapp.exception.UserNotFoundException;
import com.example.chatapp.exception.UserNotInChatGroupException;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private Chat_groupDao chat_groupDao;

    @Override
    public User getUserById(int id) {
        return userDao.selectUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(Map<String, Object> map) {
        userDao.updateUser(map);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUserById(id);
    }
    @Override
    public User getUserByUsername(String user_name) {
        return userDao.selectUserByUsername(user_name);
    }

    @Override
    public List<User> getUser(Map<String, Object> map) {
        return userDao.selectUser(map);
    }
    /**
     * 将用户添加到指定群组中的成员列表中
     *
     * @param id  群组ID
     * @param username 用户名
     * @throws UserNotFoundException   如果用户不存在
     * @throws UserAlreadyExistsException 如果用户已经存在于群组中
     */
    public void addUserToUser(int id, String username)
            throws UserNotFoundException, UserAlreadyExistsException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }

        User user2 = userDao.selectUserById(id);
        if (user2 == null) {
            throw new UserNotFoundException ("User not found: " + id);
        }

        // 检查用户是否已经存在于用户列表中
        if (user2.getMembers() != null ) {
            boolean flag = true;
            String[] tmp = user2.getMembers().split(";");
            for(String i: tmp){
                if(i.equals(username))flag=false;
            }
            if(!flag)throw new UserAlreadyExistsException("User already exists in user friend: " + username);
        }
        // 将用户添加到用户列表中
        Map<String,Object> m = new HashMap<>();
        m.put("id",id);
        m.put("username",username);
        userDao.addUserToUser(m);
    }
    /**
     * 从指定的群组中删除用户
     *
     * @param id  群组ID
     * @param username 用户名
     * @throws UserNotFoundException   如果用户不存在
     * @throws ChatGroupNotFoundException 如果群组不存在
     * @throws UserNotInChatGroupException 如果用户不在群组中
     */
    public void removeUserFromUser(int id, String username)
            throws UserNotFoundException, UserNotInChatGroupException {
        // 检查用户是否存在
        User user = userDao.selectUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }

        User user2 = userDao.selectUserById(id);
        if (user2 == null) {
            throw new UserNotFoundException ("User not found: " + id);
        }

        // 检查用户是否在群组中
        if (user2.getMembers() == null || !user2.getMembers().contains(username)) {
            throw new UserNotInChatGroupException("User not in chat group: " + username);
        }

        // 从群组中删除用户
        Map<String,Object> m = new HashMap<>();
        m.put("id",id);
        m.put("username",username);
        userDao.removeUserFromUser(m);
    }

    @Override
    public List<User> selectFriends(int id) {
        String members = userDao.selectUserById(id).getMembers();
        if(members==null)
            return null;
        members = members.replaceAll(";$", "");
        String[] names = members.split(";");
        List<User> friendList = new LinkedList<>();
        for (String name : names){
            friendList.add(userDao.selectUserByUsername(name));
        }
        return friendList;
    }

    @Override
    public List<Chat_group> selectChatGroupsContainUser(String username) {
        User user = userDao.selectUserByUsername(username);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("members",username);
        return chat_groupDao.selectChatGroup(map);
    }

    @Override
    public List<User> selectAllUsers() {
        Map<String,Object> map = new HashMap<>();
        return userDao.selectUser(map);
    }

    @Override
/**
 * 将用户添加到指定群组中的成员列表中
 *
 * @param userId  群组ID
 * @param friendName 用户名
 * @throws UserNotFoundException   如果用户不存在
 * @throws UserAlreadyExistsException 如果用户已经存在于群组中
 */
public void addFriendToUser(int userId, String friendName)
            throws UserNotFoundException, UserAlreadyExistsException {
        // 检查用户是否存在
        User friend = userDao.selectUserByUsername(friendName);
        if (friend == null) {
            throw new UserNotFoundException("Friend not found: " + friendName);
        }

        User user = userDao.selectUserById(userId);
        if (user == null) {
            throw new UserNotFoundException ("User not found: " + userId);
        }

        // 检查用户是否已经存在于用户列表中
        if (user.getMembers() != null ) {
            boolean flag = true;
            String[] tmp = user.getMembers().split(";");
            for(String i: tmp){
                if(i.equals(friendName))flag=false;
            }
            if(!flag)throw new UserAlreadyExistsException("Friend already exists in user friend: " + friendName);
        }
        // 将用户添加到用户列表中

        String members = userDao.selectUserById(userId).getMembers();
        if(members == null) members = friendName + ';';
        else members += friendName + ';';
        Map<String,Object> m = new HashMap<>();
        m.put("id",userId);
        m.put("members",members);
        userDao.updateUser(m);
    }
}
