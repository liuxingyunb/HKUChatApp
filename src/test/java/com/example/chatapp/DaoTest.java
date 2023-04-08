package com.example.chatapp;

import com.example.chatapp.dao.*;
import com.example.chatapp.model.po.*;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaoTest {

    @Autowired
    UserDao userDao;
    @Test
    public void userinsertTest() {
        userDao.insertUser(new User("Liu", "123456"));
    }

    @Test
    public void chat_group_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);

        Chat_group chatGroup=new Chat_group("Chat",1,"1");

        chatGroupdao.addChat_group(chatGroup);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void photo_wall_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Photo_wallDao photoWalldao = sqlSession.getMapper(Photo_wallDao.class);
        photoWalldao.addPhoto(new Photo_wall(1,"asdasd"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void group_chat_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Group_chatDao groupChatdao = sqlSession.getMapper(Group_chatDao.class);
        groupChatdao.insertGroup_chat(new Group_chat(1,2,"text","abcd",new Date()));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void personal_chat_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Personal_chatDao personalChatdao = sqlSession.getMapper(Personal_chatDao.class);
       personalChatdao.addPersonal_chat(new Personal_chat(1,2,"image","",new Date()));
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void chat_group_updateTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Chat_groupDao chatGroupdao = sqlSession.getMapper(Chat_groupDao.class);

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name","leo");
        m.put("id",4);
        chatGroupdao.updateChat_group(m);
        sqlSession.commit();
        sqlSession.close();
    }
}
