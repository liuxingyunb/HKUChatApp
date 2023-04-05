package com.example.chatapp;

import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import com.example.chatapp.service.impl.UserServiceImpl;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ServiceTest {
    @Test
    public void test(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(AppConfig.class);
//        context.refresh();
//
//        // 获取 UserService 组件并使用
//        UserService userService = context.getBean(UserService.class);
        SqlSession sqlSession = MybatisUtilize.getSqlsession();
        UserDao userdao = sqlSession.getMapper(UserDao.class);
        UserService userService = new UserServiceImpl(sqlSession,userdao);
        Map<String,Object> map = new HashMap<>();
        map.put("password",123);
        map.put("id",2);
        userService.updateUser(map);
    }
}
