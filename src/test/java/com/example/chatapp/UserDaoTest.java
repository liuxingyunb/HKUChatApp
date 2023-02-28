package com.example.chatapp;

import com.example.chatapp.dao.Userdao;
import com.example.chatapp.model.po.User;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class UserDaoTest {

    @Test
    public void insertTest() {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Userdao userDao = sqlSession.getMapper(Userdao.class);

        userDao.insertUser(new User(1,"jsy", "123456"));
        sqlSession.commit();
        sqlSession.close();
    }


}
