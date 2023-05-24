package com.example.chatapp.websocket;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Timer;
import java.util.TimerTask;

@ServerEndpoint("/chat")
public class ChatEndpoint {
    private static int messageCount = 0;

    @OnMessage
    public void onMessage(String message, Session session) {
        // 处理接收到的聊天信息
        // ...

        incrementMessageCount();
    }

    private static synchronized void incrementMessageCount() {
        messageCount++;
    }

    public static synchronized int getMessageCountAndReset() {
        int count = messageCount;
        messageCount = 0;
        return count;
    }

    public static void startChatStatisticsTask() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int count = getMessageCountAndReset();
                System.out.println("当前聊天信息统计：总消息数：" + count);
            }
        };

        long delay = 0;  // 延迟0毫秒，表示立即执行第一次任务
        long period = 10 * 60 * 1000;  // 十分钟的毫秒数
        timer.schedule(task, delay, period);
    }
}
