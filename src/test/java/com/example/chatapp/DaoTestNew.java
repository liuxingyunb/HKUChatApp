package com.example.chatapp;

import com.example.chatapp.dao.*;
import com.example.chatapp.model.po.*;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoTestNew {
    @Test
    public void TestInsertDao() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);
        Chat_group chatGroup = new Chat_group("group1",3,"1");
        chatGroupdao.addChat_group(chatGroup);

        UserDao userDao = (UserDao) sqlSession.getMapper(UserDao.class);
        User user = new User("Gaojiacheng", "12345");
        userDao.insertUser(user);


        Photo_wallDao photoWallDao = sqlSession.getMapper(Photo_wallDao.class);
        Photo_wall p = new Photo_wall(5,"C:\\Son.jpg");
        photoWallDao.addPhoto(p);

//        Personal_chatDao personalChatDao = sqlSession.getMapper(Personal_chatDao.class);
//        Personal_chat p = new Personal_chat();
//        personalChatDao.addPersonal_chat(new Personal_chat(2,3,"image","我是爸爸",new Date()));

        Group_chatDao groupChatDao = sqlSession.getMapper(Group_chatDao.class);
        Group_chat groupChat = new Group_chat(2,7,"text","我是爸爸们",new Date());
        groupChatDao.insertGroup_chat(groupChat);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void TestSelectDao() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        UserDao userDao = (UserDao) sqlSession.getMapper(UserDao.class);
        User user = userDao.selectUserByUsername("father");

//        Photo_wallDao photoWallDao = sqlSession.getMapper(Photo_wallDao.class);
//        Photo_wall p = photoWallDao.selectPhotosById(5);
        assertEquals(user.getId(), 16);

//        Personal_chatDao personalChatDao = sqlSession.getMapper(Personal_chatDao.class);
//        Personal_chat p = personalChatDao.selectPersonal_chatsById(2);
//        assertEquals(p.getContent(), "我是爸爸");

//        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);
//        Chat_group g = chatGroupdao.selectChat_groupById(7);
//        assertEquals(g.getName(),"group1");
//
//        Group_chatDao groupChatDao = sqlSession.getMapper(Group_chatDao.class);
//        Group_chat groupChat = groupChatDao.selectGroup_chatById(7);
//        assertEquals(groupChat.getContent(),"我是爸爸们");



        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void TestDeleteDao() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

//        Photo_wallDao photoWallDao = sqlSession.getMapper(Photo_wallDao.class);
//        photoWallDao.deletePhotoById("2");

//        Personal_chatDao personalChatDao = sqlSession.getMapper(Personal_chatDao.class);
//        personalChatDao.deletePersonal_chatById("1");

        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);
        chatGroupdao.deleteChat_group(1);

        Group_chatDao groupChatDao = sqlSession.getMapper(Group_chatDao.class);
        groupChatDao.deleteGroup_chat(6);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void TestUpdateDao() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        UserDao userDao = (UserDao) sqlSession.getMapper(UserDao.class);

        Map<String, Object> m = new HashMap<>();
        m.put("username","son6");
        m.put("mbti","son");
        m.put("id",41);
        userDao.updateUser(m);

//        Photo_wallDao photoWallDao = sqlSession.getMapper(Photo_wallDao.class);
//        Map<String, Object> m = new HashMap<>();
//        m.put("photo_url","desktop/Son");
//        m.put("id",3);
//        photoWallDao.updatePhoto_wall(m);

//        Personal_chatDao personalChatDao = sqlSession.getMapper(Personal_chatDao.class);
//        Map<String, Object> m = new HashMap<>();
//        m.put("content","我是儿子");
//        m.put("id",2);
//        personalChatDao.updatePersonal_chat(m);

//        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        Map<String, Object> m = new HashMap<>();
//
//        m.put("username","Son");
//        m.put("id",2);
//        m.put("members","liu");
//        chatGroupdao.addUserToGroup(m);
//        userDao.addUserToUser(m);


//        Group_chatDao groupChatdao = sqlSession.getMapper(Group_chatDao.class);
//        Map<String, Object> m = new HashMap<>();
//        m.put("content","我是儿子们");
//        m.put("id",5);
//        groupChatdao.updateGroup_chat(m);



        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testGroup(){
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);
        Map<String, Object> m = new HashMap<>();
        m.put("members","Liu");
        List<Chat_group> l = chatGroupdao.selectChatGroup(m);
        assertEquals(l.get(0).getId(),8);
        assertEquals(l.size(),1);

    }
    @Test
    public void add(){
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        for(int i = 1; i < 41; i++){
            Map<String,Object> m = new HashMap<>();
            m.put("id",41);
            m.put("username","li");
            userDao.removeUserFromUser(m);
//        }

//        List<User> u = userDao.selectUser(m);
//        User u2 = userDao.selectUserByUsername("Son");
//        userDao.addUserToUser(m);
//        System.out.println(u.get(0));
//        System.out.println(u2);
        sqlSession.commit();
        sqlSession.close();
    }
}
