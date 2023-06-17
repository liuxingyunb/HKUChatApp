package com.example.chatapp.controller;

import com.example.chatapp.model.po.User;
import com.example.chatapp.model.vo.Response;
import com.example.chatapp.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@ComponentScan("com.example.chatapp.*")
@RestController
@RequestMapping("/chatGPT")
public class ChatGPTController {
    @Autowired
    private ChatGPTService chatGPTService;
    @PostMapping("/getResult")
    public Response getResult(@RequestParam String question) {
        return Response.ok("answer:", chatGPTService.computeQuestion(question));
    }

}
