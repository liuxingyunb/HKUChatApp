package com.bupt.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * 统一响应封装
 */

@Data
public class Response {

    private  String status;
    private  String msg;
    private  Object data;

    public Response(String status){
        this.status=status;
    }

    public Response(String status,String msg,Object data) {
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public Response(String status,String msg){
        this.msg=msg;
        this.status=status;
    }

    public static Response ok(){
        return new Response("ok");

    }

    public static Response error(){
        return new Response("error");

    }

    public static Response ok(String msg){
        return new Response("ok",msg);

    }

    public static Response error(String msg){
        return new Response("error",msg);

    }


    public static Response ok(String msg,Object data){
        return new Response("ok",msg,data);

    }

    public static Response error(String msg,Object data){
        return new Response("error",msg,data);

    }

}
