package com.bupt.controller;

import com.bupt.model.vo.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 控制器
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/pin")
    public Response pin() {
        return Response.ok();
    }

    @GetMapping("/db")
    public Response db() {
        return Response.ok();
    }
}
