package com.example.chatapp.model.vo;

import lombok.Data;

@Data
public class Message {

    String id;
    String type;//text,video,image,system(仅在发送时需要用到发给前端信息的)
    Integer fromId;
    Integer toId;
    Integer isGroup;//0,1
    Integer isLast;//0,1仅发送大文件时用于标识
    byte[] data;//仅发送大文件时有用
    String content;//仅发送
    String sender_avatar_url;
    String sender_name;
    String timeStamp;//由server端计时

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

