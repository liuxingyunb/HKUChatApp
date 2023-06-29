package com.example.chatapp.controller;


import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.ChatService;
import com.example.chatapp.service.UserService;
import com.example.chatapp.utils.MultiFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//该文件用来处理聊天期间多媒体文件的传输及显示,交互的字符串为名字
@ComponentScan("com.example.chatapp.*")
@RestController
@Api(tags = "File transfer")
@RequestMapping("/file")
public class FileController {
    @Autowired
    UserService userService;


    @ApiOperation(value = "Receive file")
    @PostMapping("/get")
    public Response fileAccept(@RequestParam("data") MultipartFile file,@RequestParam("name") String name) throws Exception{
        String path = MultiFile.fileStore(file.getBytes(),name);
        return Response.ok(path);//返回文件路径
    }

    @ApiOperation(value = "Receive avatar")
    @PostMapping("/get-avatar")
    public Response fileAcceptAvatar(@RequestParam("data") MultipartFile file) throws Exception{
        String name=file.getOriginalFilename();
        String path = MultiFile.fileStore(file.getBytes(),name);
        int id=Integer.parseInt(name.split("_")[0]);

        Map<String, Object> propertyValueMap = new HashMap<>();
        propertyValueMap.put("id",id);
        propertyValueMap.put("avatar_url", name);
        userService.updateUser(propertyValueMap);
        return Response.ok(name);//返回文件路径
    }

    @ApiOperation(value = "Send file")
    @GetMapping("/post")
    public Response fileSend(@PathParam("name") String name, HttpServletResponse response) throws Exception{
        MultiFile.fileGet(name,response);
        return Response.ok();
    }

    @ApiOperation(value = "delete file")
    @PostMapping("/delete")
    public Response fileDelete(@RequestParam("name") String name) throws Exception{
        MultiFile.fileDelete(name);
        return Response.ok();
    }

}