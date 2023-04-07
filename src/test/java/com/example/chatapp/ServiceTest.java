package com.example.chatapp;

import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.User;
import com.example.chatapp.service.UserService;
import com.example.chatapp.service.impl.UserServiceImpl;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
@SpringBootApplication
@ComponentScan("com.example.chatapp.*")
@ServletComponentScan("com.example.chatapp.*")
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Test
    public void test(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(AppConfig.class);
//        context.refresh();
//
//        // 获取 UserService 组件并使用
//        UserService userService = context.getBean(UserService.class);
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("password","1234");
        userDao.updateUser(map);
    }
}
