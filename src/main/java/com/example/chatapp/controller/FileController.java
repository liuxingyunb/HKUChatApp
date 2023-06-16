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
import java.util.List;

//该文件用来处理聊天期间多媒体文件的传输及显示,交互的字符串为名字
@ComponentScan("com.example.chatapp.*")
@RestController
@Api(tags = "File transfer")
@RequestMapping("/file")
public class FileController {


    @ApiOperation(value = "Receive file")
    @PostMapping("/get")
    public Response fileAccept(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws Exception{
        String path = MultiFile.fileStore(file.getBytes(),name);
        return Response.ok(path);//返回文件路径
    }

    @ApiOperation(value = "Send file")
    @PostMapping("/post")
    public Response fileSend(@RequestParam("name") String name, HttpServletResponse response) throws Exception{
        String path = MultiFile.nameToPath.get(name);
        MultiFile.fileGet(path,response);
        return Response.ok();
    }

    @ApiOperation(value = "delete file")
    @PostMapping("/delete")
    public Response fileDelete(@RequestParam("name") String name) throws Exception{
        MultiFile.fileDelete(name);
        return Response.ok();
    }

}