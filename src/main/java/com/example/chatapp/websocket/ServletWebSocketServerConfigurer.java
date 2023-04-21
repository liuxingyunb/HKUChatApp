package com.example.chatapp.websocket;


import com.example.chatapp.websocket.ServletWebSocketServerHandler;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.net.http.WebSocket;

@Configuration
@EnableWebSocket
public class ServletWebSocketServerConfigurer implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry
                //添加处理器到对应的路径
                .addHandler(new ServletWebSocketServerHandler(),"")
                .setAllowedOrigins("*");
    }
}