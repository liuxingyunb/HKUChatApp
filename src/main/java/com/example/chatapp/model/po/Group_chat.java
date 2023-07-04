package com.example.chatapp.model.po;

import java.util.Date;

public class Group_chat{
    int id;
    int sender_id;
    int group_id;
    String message_type;//text,video,image,system
    String content;
    Date send_time;

    public Group_chat() {}

    public Group_chat(int sender_id, int group_id, String message_type, String content, Date send_time) {
        this.sender_id = sender_id;
        this.group_id = group_id;
        this.message_type = message_type;
        this.content = content;
        this.send_time = send_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }
}

