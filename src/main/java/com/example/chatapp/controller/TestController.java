package com.example.chatapp.controller;

import com.bupt.model.vo.Response;

import com.example.chatapp.dao.Chat_groupDao;
import com.example.chatapp.dao.UserDao;
import com.example.chatapp.model.po.Chat_group;
import com.example.chatapp.model.po.User;
import com.example.chatapp.utilize.MybatisUtilize;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试 控制器
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserDao userdao;
    @Autowired
    Chat_groupDao chat_groupDao;

    @PostMapping("/pin")
    public Response pin(@RequestBody User user) {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        userDao.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
        return Response.ok("fuck",user);
    }
    @PostMapping("/pin2")
    public Response pin2(int id) {
        SqlSession sqlSession = MybatisUtilize.getSqlsession();

        Chat_groupDao chat_groupDao = sqlSession.getMapper(Chat_groupDao.class);

        chat_groupDao.deleteChat_group(id);
        sqlSession.commit();
        sqlSession.close();
        return Response.ok("fuck",id);
    }

    @GetMapping("/db")
    public Response db() {
        return Response.ok();
    }
}
