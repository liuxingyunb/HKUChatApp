package com.example.chatapp;

import com.example.chatapp.dao.*;
import com.example.chatapp.model.po.*;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DaoTest {

    @Test
    public void userinsertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Userdao userDao = sqlSession.getMapper(Userdao.class);

        userDao.insertUser(new User(2,"lll", "123456"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void chat_group_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Chat_groupdao chatGroupdao = sqlSession.getMapper(Chat_groupdao.class);

        Chat_group chatGroup=new Chat_group(1,"Chat",1,"1");
        chatGroupdao.addchat_group(chatGroup);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void photo_wall_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Photo_walldao photoWalldao = sqlSession.getMapper(Photo_walldao.class);
        photoWalldao.addphoto(new Photo_wall(1,1,"asdasd"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void group_chat_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Group_chatdao groupChatdao = sqlSession.getMapper(Group_chatdao.class);
        groupChatdao.addChat_group(new Group_chat(1,1,2,"text","abcd",new Date()));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void personal_chat_insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        Personal_chatdao personalChatdao = sqlSession.getMapper(Personal_chatdao.class);
       personalChatdao.addPersonal_chat(new Personal_chat(1,1,2,"image","",new Date()));
        sqlSession.commit();
        sqlSession.close();
    }

}
