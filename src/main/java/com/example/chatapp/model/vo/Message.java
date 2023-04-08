package com.example.chatapp.model.vo;

import lombok.Data;

@Data
public class Message {

    String type;//text,video,image,system(仅在发送时需要用到发给前端信息的)
    Integer fromId;
    Integer toId;
    Integer isGroup;//0,1
    Integer isLast;//0,1仅发送大文件时用于标识
    byte[] data;//仅发送大文件时有用
    String content;//仅发送
    String timeStamp;//由server端计时

}
