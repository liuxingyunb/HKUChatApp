package com.example.chatapp.model.po;

import java.util.Date;

public class Chat_group {

    private int id;
    private String name;
    private String ownerId;
    private String members;
    private Date create_time;
    private Date last_active_time;

    public Chat_group(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_active_time() {
        return last_active_time;
    }

    public void setLast_active_time(Date last_active_time) {
        this.last_active_time = last_active_time;
    }
}


